package com.graduation.blog.service;

import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.LoginRequestDTO;
import java.util.List;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
public interface UserService {

  /**
   * id 查询
   */
  User selectUserById(String id);

  /**
   * 用户名密码登录
   */
  LoginInfoResponseDTO login(LoginRequestDTO dto);

  /**
   * 用户名密码登录
   */
  List<User> listUser();


}
