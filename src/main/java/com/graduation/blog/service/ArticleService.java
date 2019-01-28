package com.graduation.blog.service;

import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.domain.dto.requestdto.AuditBlogRequestDTO;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
public interface ArticleService {


  /**
   *  发表博文
   */
  void publishBlog(String userId, ArticlePublishRequestDTO articlePublishRequestDTO);

  /**
   *  单个审核博文
   */
  void auditBlog(String userId, AuditBlogRequestDTO abRequestDTO);


  /**
   *  删除博文
   */
  void deleteBlog(String articleId);


}
