package com.graduation.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.blog.dao.ArticleMapper;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.domain.dto.requestdto.AuditBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.BlogsQueryRequestDTO;
import com.graduation.blog.enums.AuditClassEnum;
import com.graduation.blog.enums.UserTypeEnum;
import com.graduation.blog.service.ArticleService;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.CommonsUtils;
import com.graduation.blog.utils.ErrorCode;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  ArticleMapper articleMapper;
  @Autowired
  UserMapper userMapper;



  @Override
  @Transactional(rollbackFor = Exception.class)
  public void publishBlog(String userId, ArticlePublishRequestDTO articlePublishRequestDTO) {
    Article article = BeanConvertUtils.copyBean(articlePublishRequestDTO, Article.class);
    article.setId(CommonsUtils.get32BitUUID());
    article.setUserId(userId);
    article.setAudit(AuditClassEnum.WAIT_AUDIT.getCode());
    articleMapper.insert(article);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void auditBlog(String userId, AuditBlogRequestDTO auditBlogRequestDTO) {
    User user = userMapper.selectByPrimaryKey(userId);
    if (UserTypeEnum.ORDINARY.getCode().equals(user.getAuthority())) {
      throw new AppException(ErrorCode.ACCESS_DENIED, "权限不足");
    }
    Article article = articleMapper.selectByPrimaryKey(auditBlogRequestDTO.getArticleId());
    Assert.isNotNull(article, ErrorCode.RESULT_EMPTY, "该文章不存在");
    article.setAudit(auditBlogRequestDTO.getAuditStr());
    articleMapper.updateByPrimaryKeySelective(article);

  }



  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteBlog(String articleId) {
    articleMapper.deleteByPrimaryKey(articleId);
  }

  @Override
  public Article selectBlog(String articleId) {
    return articleMapper.selectByPrimaryKey(articleId);
  }

  @Override
  public PageInfo<Article> myBlogList(String userId, BlogsQueryRequestDTO bqrDTO) {
    Example example = new Example(Article.class);
    example.createCriteria().andEqualTo("userId", userId)
      .andEqualTo("status", "0").andEqualTo("audit", "1");
    List<Article> articles = articleMapper.selectByExample(example);
    PageInfo<Article> articlePageInfo = PageHelper.startPage(Integer.valueOf(bqrDTO.getPageNum()),
        Integer.valueOf(bqrDTO.getPageSize()), true)
        .doSelectPageInfo(() -> articleMapper.selectByExample(example));
    return articlePageInfo;
  }
}
