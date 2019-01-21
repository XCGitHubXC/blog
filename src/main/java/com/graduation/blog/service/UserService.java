package com.graduation.blog.service;

import com.graduation.blog.domain.User;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
public interface UserService {


  User selectUserById(String id);
}
