package com.graduation.blog.service;

import java.util.List;

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
   * 我的关注用户
   */
  List<String> myFocus(String curUserId);

  /**
   * 我的粉丝
   */
  List<String> myFans(String curUserId);

}
