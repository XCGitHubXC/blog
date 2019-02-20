package com.graduation.blog.dao;


import com.graduation.blog.base.MyMapper;
import com.graduation.blog.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MyMapper<User> {

  /**
   * 用户名密码登录
   */
  User selectByLoginNameAndPwd(@Param("loginName")String loginName, @Param("password")String password);

  /**
   * 用户是否存在
   */
  User selectByLoginName(@Param("loginName")String loginName);


  /**
   * 根据ids查询
   */
  List<User> selectByIds(List<String> ids);
}