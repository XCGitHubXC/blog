package com.graduation.blog.service;

import java.util.List;

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
   * 我的收藏
   */
  List<String> myStores(String curUserId);

}
