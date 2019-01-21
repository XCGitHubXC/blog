package com.graduation.blog.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class BeanConvertUtils {

  public static <T> T copyBean(Object sourceObj, Class<T> targetCls) {
    T targetObj = null;
    if (sourceObj != null) {
      try {
        targetObj = BeanUtils.instantiateClass(targetCls);
        BeanUtils.copyProperties(sourceObj, targetObj);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return targetObj;
  }

  public static <T> List<T> copyList(Collection<?> list, Class<T> targetCls) {
    List<T> result = new ArrayList<T>();
    if (list == null || list.size() == 0) {
      return result;
    }

    for (Object obj : list) {
      result.add(copyBean(obj, targetCls));
    }

    return result;
  }

  /**
   * 将集合中的map转为对应的dto
   */
  public static <T> List<T> map2Java(List<Map<String, Object>> list, Class<T> targetCls) {
    if (CollectionUtils.isEmpty(list)) {
      return new ArrayList<T>();
    }
    List<T> resultList = new ArrayList<>();
    try {
      for (int i = 0; i < list.size(); i++) {
        Map map = list.get(i);
        Set<Entry> set = map.entrySet();
        T targetObject = targetCls.newInstance();
        for (Entry entry : set) {
          Field field = targetCls.getDeclaredField(entry.getKey().toString());
          field.setAccessible(true);
          field.set(targetObject, entry.getValue().toString());
        }
        resultList.add(targetObject);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
  }

  /**
   * 深度拷贝list 集合
   */
  public static <T> List<T> deepCopy(List<T> src) {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(byteOut);
      out.writeObject(src);
      ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
      ObjectInputStream in = new ObjectInputStream(byteIn);
      @SuppressWarnings("unchecked")
      List<T> dest = (List<T>) in.readObject();
      return dest;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
  public static Map<String, Object> transBean2Map(Object obj) {

    if (obj == null) {
      return null;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();

        // 过滤class属性
        if (!"class".equals(key)) {
          // 得到property对应的getter方法
          Method getter = property.getReadMethod();
          Object value = getter.invoke(obj);

          map.put(key, value);
        }

      }
    } catch (Exception e) {
      System.out.println("transBean2Map Error " + e);
    }

    return map;

  }
}
