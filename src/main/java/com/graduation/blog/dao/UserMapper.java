package com.graduation.blog.dao;


import com.graduation.blog.base.MyMapper;
import com.graduation.blog.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MyMapper<User> {

//  int deleteByPrimaryKey(String id);
//
//  int insert(User record);
//
//  int insertSelective(User record);
//
//  User selectByPrimaryKey(String id);
//
//  int updateByPrimaryKeySelective(User record);
//
//  int updateByPrimaryKey(User record);

  /**
   * 用户名密码登录
   */
  User selectByLoginNameAndPwd(@Param("loginName")String loginName, @Param("password")String password);
}