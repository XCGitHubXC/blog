package com.graduation.blog.service;

import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;

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
}
