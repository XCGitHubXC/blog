package com.graduation.blog.controller;

import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.LoginRequestDTO;
import com.graduation.blog.domain.dto.LoginTokenResponseDTO;
import com.graduation.blog.domain.dto.RefreshTokenRequestDTO;
import com.graduation.blog.domain.dto.RefreshTokenResponseDTO;
import com.graduation.blog.domain.dto.RegisterRequestDTO;
import com.graduation.blog.security.TokenGenerator;
import com.graduation.blog.service.UserService;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@RestController
@RequestMapping("/user")
@Api(value = "用户查询", tags = "用户查询")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private TokenGenerator tokenGenerator;

  @GetMapping("/select/{id}")
  @ApiOperation(value = "查询用户", notes = "查询用户")
  public Result<User> selectByPrimKey(@PathVariable("id") String id) {
    User user = userService.selectUserById(id);
    return Result.success(user);
  }


  @GetMapping("/select/allUser")
  @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
  public Result<List<User>> selectByPrimKey() {
    List<User> users = userService.listUser();
    return Result.success(users);
  }

  /**
   * 刷新token
   */
  @ApiOperation(value = "刷新token", notes = "刷新token")
  @RequestMapping(value = "refreshToken", method = RequestMethod.POST)
  public Result<RefreshTokenResponseDTO> refreshToken(@RequestBody @Valid RefreshTokenRequestDTO dto) {
    RefreshTokenResponseDTO res = tokenGenerator.refreshToken(dto.getRefreshToken());
    return Result.success(res);
  }

  /**
   * 用户名密码登录
   */
  @ApiOperation(value = "用户名密码登录", notes = "用户名密码登录")
  @RequestMapping(value = "userLogin", method = RequestMethod.POST)
  public Result<LoginTokenResponseDTO> userLogin(@RequestBody @Valid LoginRequestDTO dto) {
    LoginInfoResponseDTO loginInfo = userService.login(dto);
    LoginTokenResponseDTO res = tokenGenerator.generateLoginToken(loginInfo);
    return Result.success(res);
  }

  /**
   * 用户注册
   */
  @ApiOperation(value = "用户注册", notes = "用户注册")
  @RequestMapping(value = "userRegister", method = RequestMethod.POST)
  public Result userRegister(@RequestBody @Valid RegisterRequestDTO dto) {
    userService.userRegister(dto);
    return Result.success();
  }



}
