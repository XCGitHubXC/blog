package com.graduation.blog.service;

import com.github.pagehelper.PageInfo;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.PageParam;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
public interface FocusService {

  /**
   * 关注用户
   */
  void focusUser(String curUserId, String focusUserId);

  /**
   * 取消关注
   */
  void cancelFocusUser(String curUserId, String focusUserId);


  /**
   * 我的关注用户
   */
  PageInfo<User> myFocus(String curUserId, PageParam pageParam);

  /**
   * 我的粉丝
   */
  PageInfo<User> myFans(String curUserId, PageParam pageParam);

}
