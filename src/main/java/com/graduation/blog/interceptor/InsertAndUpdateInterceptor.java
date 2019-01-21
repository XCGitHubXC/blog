package com.graduation.blog.interceptor;


import com.graduation.blog.constants.BlogConst;
import com.graduation.blog.enums.StatusEnum;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.ContextUtil;
import com.graduation.blog.utils.ErrorCode;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * mybaties拦截器，对公共字段（创建人、创建时间、修改人、修改时间、status）赋值
 *
 *
 */
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
    Object.class})})
@Component
public class InsertAndUpdateInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    SqlCommandType sqlCommandType = SqlCommandType.UNKNOWN;
    Object[] args = invocation.getArgs();
    MappedStatement mappedStatement = null; //第一个参数
    int mappedStatementIndex = 0; //记录mappedStatement的位置
    Object parameterObject = null; //第二个参数
    // 遍历处理所有参数，update方法有两个参数，参见Executor类中的update()方法。
    for (int i = 0; i < args.length; i++) {
      Object arg = args[i];
      if (arg instanceof MappedStatement) {
        mappedStatementIndex = i;
        mappedStatement = (MappedStatement) arg;
        sqlCommandType = mappedStatement.getSqlCommandType();
      } else {
        parameterObject = args[i];
      }
    }
    if (sqlCommandType == SqlCommandType.INSERT || sqlCommandType == SqlCommandType.UPDATE) {
      // 如果是“增加”或“更新”操作，则继续进行默认操作信息赋值。否则，则退出
      args[mappedStatementIndex] = this.updateSql(sqlCommandType, mappedStatement, parameterObject);
      // 第二个参数处理
      if (parameterObject != null && parameterObject instanceof Map) {
        // 如果是map，有两种情况：（1）使用@Param多参数传入，由Mybatis包装成map。（2）原始传入Map
        setPropertyForMap(sqlCommandType, parameterObject);
      } else { // 原始参数传入
        setProperty(sqlCommandType, parameterObject);
      }
    }
    return invocation.proceed();
  }

  /**
   * 更新sql，针对mapper.xml里的<update>..........</update>和mapper公共的更新方法updateByPrimaryKey、updateByPrimaryKeySelective方法
   */
  private MappedStatement updateSql(SqlCommandType sqlCommandType, MappedStatement mappedStatement,
      Object baseObject) {
    if (sqlCommandType != SqlCommandType.UPDATE) {
      return mappedStatement;
    }
    try {
      SqlSource sqlSource = mappedStatement.getSqlSource();
      if (sqlSource instanceof DynamicSqlSource && !(baseObject instanceof Map)) {
        //mapper公共的更新方法updateByPrimaryKey、updateByPrimaryKeySelective，设置版本号条件，乐观锁
        DynamicSqlSource dynamicSqlSource = (DynamicSqlSource) sqlSource;
        BoundSql boundSql = dynamicSqlSource.getBoundSql(baseObject);
        String updateSql = boundSql.getSql();
        if (updateSql.contains(" WHERE  Id =")) {
          Integer version = getVersionValue(baseObject);
          updateSql = updateSql
              .replace(" WHERE  Id =", " WHERE version = " + version + " and Id =");
          // 把新的查询放到statement里
          MappedStatement newMappedStatement = copyFromMappedStatement(mappedStatement, boundSql,
              updateSql);
          return newMappedStatement;
        }
      } else if (sqlSource instanceof RawSqlSource) { // 如果是写在mapper的更新语句<update>.......</update>，截取语句,对修改人和修改时间设置
        BoundSql boundSql = sqlSource.getBoundSql(null);
        String updateSql = boundSql.getSql();
        String replaceString = "admin".equalsIgnoreCase(this.getCurrentUserName()) ? " set update_time=now()," : " set update_user='" + this.getCurrentUserName() + "',update_time=now(),";
        if (updateSql.contains("update")) {
          updateSql = updateSql.replace(" set ", replaceString);
          // 把新的查询放到statement里
          MappedStatement newMappedStatement = copyFromMappedStatement(mappedStatement, boundSql,
              updateSql);
          return newMappedStatement;
        }
      }
    } catch (Exception e) {
      log.error("InsertAndUpdateInterceptor updateSql error {}", e);
    }
    return mappedStatement;
  }

  /**
   * copy新的一个MappedStatement
   */
  private MappedStatement copyFromMappedStatement(MappedStatement ms, BoundSql boundSql,
      String sql) {
    BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
        boundSql.getParameterObject());
    SqlSource newSqlSource = new SqlSource() {
      @Override
      public BoundSql getBoundSql(Object parameterObject) {
        return newBoundSql;
      }
    };

    MappedStatement.Builder builder =
        new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
            ms.getSqlCommandType());
    builder.resource(ms.getResource());
    builder.fetchSize(ms.getFetchSize());
    builder.statementType(ms.getStatementType());
    builder.keyGenerator(ms.getKeyGenerator());
    if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
      builder.keyProperty(ms.getKeyProperties()[0]);
    }
    builder.timeout(ms.getTimeout());
    builder.parameterMap(ms.getParameterMap());
    builder.resultMaps(ms.getResultMaps());
    builder.resultSetType(ms.getResultSetType());
    builder.cache(ms.getCache());
    builder.flushCacheRequired(ms.isFlushCacheRequired());
    builder.useCache(ms.isUseCache());
    return builder.build();
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties obj) {

  }

  @SuppressWarnings("rawtypes")
  private void setPropertyForMap(SqlCommandType sqlCommandType, Object arg) {
    if (arg == null) {
      return;
    }
    Map map = (Map) arg;
    Integer version = null; //设置版本号条件
    for (Object key : map.keySet()) {
      Object value = map.get(key);
      //公共mapper的updateByExample、updateByExampleSelective方法，参数包含record和example
      if ("record".equals(key)) { //获取原来的版本号
        try {
          version = this.getVersionValue(value);
        } catch (Exception e) {
          log.error("InsertAndUpdateInterceptor setPropertyForMap error {}", e);
        }
      } else if ("example".equals(key)
          && value instanceof Example) { //针对公共mapper的updateByExample、updateByExampleSelective方法对更新操作设置版本号条件
        Example example = (Example) value;
        List<Criteria> criterias = example.getOredCriteria();
        // 更新时，条件不能为空，否则抛出异常
        if (CollectionUtils.isEmpty(criterias)) {
          throw new AppException(ErrorCode.FAIL_DATABASE);
        }
        for (Criteria criteria : criterias) {
          if (CollectionUtils.isEmpty(criteria.getCriteria())) {
            throw new AppException(ErrorCode.FAIL_DATABASE);
          }
        }
        example.and(example.createCriteria().andEqualTo("version", version));
        continue;
      } else if ((String.valueOf(key)).contains("param")) {
        continue;
      }
      if (value instanceof List) {
        List list = (List) value;
        for (Object model : list) {
          setProperty(sqlCommandType, model);
        }
      } else {
        setProperty(sqlCommandType, value);
      }
    }
  }

  /**
   * 为对象的操作属性赋值
   */
  private void setProperty(SqlCommandType sqlCommandType, Object model) {
    if (model == null) {
      return;
    }
    Field[] fields = model.getClass().getSuperclass().getDeclaredFields();
    try {
      for (int i = 0; i < fields.length; i++) { // 遍历所有属性
        String fieldName = fields[i].getName(); // 获取属性的名字
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method m = model.getClass().getMethod("get" + fieldName);
        if (sqlCommandType == SqlCommandType.INSERT) {
          if ("createUser".equalsIgnoreCase(fieldName)) {
            m = model.getClass().getMethod("set" + fieldName, String.class);
            m.invoke(model, this.getCurrentUserName());
          }
          if ("createTime".equalsIgnoreCase(fieldName)) {
            m = model.getClass().getMethod("set" + fieldName, Date.class);
            m.invoke(model, new Date());
          }
          if ("status".equalsIgnoreCase(fieldName)) {
            m = model.getClass().getMethod("get" + fieldName);
            String status = (String) m.invoke(model);
            // 对status赋值
            m = model.getClass().getMethod("set" + fieldName, String.class);
            m.invoke(model, StringUtils.isBlank(status) ? StatusEnum.USED.getCode() : status);
          }
          if ("version".equalsIgnoreCase(fieldName)) {
            m = model.getClass().getMethod("set" + fieldName, Integer.class);
            m.invoke(model, BlogConst.DEFAULT_VERSION);
          }
        } else if (sqlCommandType == SqlCommandType.UPDATE) {
          if ("updateUser".equalsIgnoreCase(fieldName) && !"admin".equalsIgnoreCase(this.getCurrentUserName())) {
            m = model.getClass().getMethod("set" + fieldName, String.class);
            m.invoke(model, this.getCurrentUserName());
          }
          if ("updateTime".equalsIgnoreCase(fieldName)) {
            m = model.getClass().getMethod("set" + fieldName, Date.class);
            m.invoke(model, new Date());
          }
          if ("version".equalsIgnoreCase(fieldName)) {
            m = model.getClass().getMethod("get" + fieldName);
            Integer version = (Integer) m.invoke(model);
            m = model.getClass().getMethod("set" + fieldName, Integer.class);
            m.invoke(model, version == null ? BlogConst.DEFAULT_VERSION : version + 1);
          }
        }
      }
    } catch (Exception e) {
      log.error("InsertAndUpdateInterceptor setProperty error {}", e);
    }
  }

  /**
   * 通过baseObject获取version的值
   */
  private Integer getVersionValue(Object baseObject) {
    Integer version = null;
    if (!(baseObject instanceof Map)) {
      try {
        Field field = baseObject.getClass().getSuperclass().getDeclaredField("version");
        if (field == null) {
          return version;
        }
        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method m = baseObject.getClass().getMethod("get" + fieldName);
        version = (Integer) m.invoke(baseObject);
      } catch (Exception e) {
        log.error("InsertAndUpdateInterceptor getVersionValue error {}", e);
      }
    }
    return version;
  }

  /**
   * 获取当前用户
   */
  private String getCurrentUserName() {
    String userName = "admin";
    try {
      userName = ContextUtil.getCurrentUserId();
    } catch (Exception e) {
      log.error("InsertAndUpdateInterceptor 获取当前用户出错");
    }
    return userName;
  }

}
