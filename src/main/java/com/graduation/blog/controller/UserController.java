package com.graduation.blog.controller;

import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.requestdto.LoginRequestDTO;
import com.graduation.blog.domain.dto.requestdto.RefreshTokenRequestDTO;
import com.graduation.blog.domain.dto.requestdto.RegisterRequestDTO;
import com.graduation.blog.domain.dto.requestdto.UserMsgUpdateRequestDTO;
import com.graduation.blog.domain.dto.requestdto.UserPwdUpdateRequestDTO;
import com.graduation.blog.domain.dto.responsedto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.responsedto.LoginTokenResponseDTO;
import com.graduation.blog.domain.dto.responsedto.RefreshTokenResponseDTO;
import com.graduation.blog.security.TokenGenerator;
import com.graduation.blog.service.UserService;
import com.graduation.blog.utils.ContextUtil;
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
@Api(value = "用户模块", tags = "用户模块")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private TokenGenerator tokenGenerator;

  @GetMapping("/select/{id}")
  @ApiOperation(value = "用户资料", notes = "用户资料")
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



  /**
   * 用户基本信息修改
   */
  @ApiOperation(value = "用户基本信息修改", notes = "用户基本信息修改")
  @RequestMapping(value = "userMsgUpdate", method = RequestMethod.POST)
  public Result userMsgUpdate(@RequestBody @Valid UserMsgUpdateRequestDTO dto) {
    // 获得当前用户的id
    String currentUserId = ContextUtil.getCurrentUserId();
    userService.userMsgUpdate(dto, currentUserId);
    return Result.success();
  }

  /**
   * 用户头像修改
   */
  @ApiOperation(value = "用户头像修改", notes = "用户头像修改")
  @RequestMapping(value = "userHeadUpdate/{fileId}", method = RequestMethod.GET)
  public Result userHeadUpdate(@PathVariable String fileId) {
    // 获得当前用户的id
    String currentUserId = ContextUtil.getCurrentUserId();
    userService.userHeadUpdate(fileId, currentUserId);
    return Result.success();
  }



  /**
   * 用户密码修改
   */
  @ApiOperation(value = "用户密码修改", notes = "用户密码修改")
  @RequestMapping(value = "userPwdUpdate", method = RequestMethod.POST)
  public Result userPwdUpdate(@RequestBody @Valid UserPwdUpdateRequestDTO dto) {
    String currentUserId = ContextUtil.getCurrentUserId();
    userService.userPwdUpdate(dto, currentUserId);
    return Result.success();
  }

  /**
   * 用户信息统计
   */
  @ApiOperation(value = "用户信息统计", notes = "用户信息统计")
  @RequestMapping(value = "userInfoStatistics/{userId}", method = RequestMethod.GET)
  public Result userInfoStatistics(@PathVariable String userId) {



    return Result.success();
  }

}
