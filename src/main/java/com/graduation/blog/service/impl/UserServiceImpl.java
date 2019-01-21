package com.graduation.blog.service.impl;

import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.User;
import com.graduation.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;


  @Override
  public User selectUserById(String id) {
    return userMapper.selectByPrimaryKey(id);
  }
}
