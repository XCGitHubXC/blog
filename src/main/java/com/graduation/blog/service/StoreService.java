package com.graduation.blog.service;

import com.github.pagehelper.PageInfo;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.dto.PageParam;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
public interface StoreService {

  /**
   * 收藏文章
   */
  void storeBlog(String curUserId, String articleId);

  /**
   * 取消收藏文章
   */
  void cancelStoreBlog(String curUserId, String articleId);

  /**
   * 我的收藏
   */
  PageInfo<Article> myStores(String curUserId, PageParam pageParam);

}
