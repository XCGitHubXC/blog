package com.graduation.blog.service.impl;

import com.graduation.blog.dao.ArticleMapper;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.enums.AuditClassEnum;
import com.graduation.blog.service.ArticleService;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.CommonsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


  @Override
  public void publishBlog(String userId, ArticlePublishRequestDTO articlePublishRequestDTO) {
    Article article = BeanConvertUtils.copyBean(articlePublishRequestDTO, Article.class);
    article.setId(CommonsUtils.get32BitUUID());
    article.setUserId(userId);
    article.setAudit(AuditClassEnum.WAIT_AUDIT.getCode());
    articleMapper.insert(article);
  }
}
