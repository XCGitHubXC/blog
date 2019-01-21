package com.graduation.blog.controller;

import com.graduation.blog.domain.User;
import com.graduation.blog.service.UserService;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @GetMapping("/select/{id}")
  @ApiOperation(value = "查询用户", notes = "查询用户")
  public Result selectByPrimKey(@PathVariable("id") String id) {
    User user = userService.selectUserById(id);
    return Result.success(user);
  }



  @GetMapping("/login")
  @ApiOperation(value = "用户登录", notes = "用户登录")
  public User login(String id) {
    return null;
  }

}
