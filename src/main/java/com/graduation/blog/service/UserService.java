package com.graduation.blog.service;

import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.requestdto.UserMsgUpdateRequestDTO;
import com.graduation.blog.domain.dto.requestdto.UserPwdUpdateRequestDTO;
import com.graduation.blog.domain.dto.responsedto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.requestdto.LoginRequestDTO;
import com.graduation.blog.domain.dto.requestdto.RegisterRequestDTO;
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
   * 所有用户
   */
  List<User> listUser();

  /**
   * 用户注册
   */
  void userRegister(RegisterRequestDTO dto);


  /**
   * 用户密码修改
   */
  void userPwdUpdate(UserPwdUpdateRequestDTO dto, String userId);


  /**
   * 用户信息修改
   */
  void userMsgUpdate(UserMsgUpdateRequestDTO dto, String userId);

  /**
   * 用户头像修改
   */
  void userHeadUpdate(String fileId, String userId);


}
