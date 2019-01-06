package com.graduation.blog.controller;

import com.graduation.blog.domain.WpUsers;
import com.graduation.blog.service.WpUsersService;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
@RequestMapping("/wpUsers")
@Api(value = "用户查询", tags = "用户查询")
public class WpUsersController {

  @Autowired
  private WpUsersService wpUsersService;

  @GetMapping("/select/{id}")
  @ApiOperation(value = "查询用户", notes = "查询用户")
  public Result selectByPrimKey(@PathVariable("id") Long id) {
    WpUsers wpUsers = wpUsersService.selectByPrimaryKey(id);
    return Result.success(wpUsers);
  }

  @GetMapping("/selectAll")
  @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
  public Result selectAll() {
    List<WpUsers> wpUsers = wpUsersService.listWpUsers();
    return Result.success(wpUsers);
  }

  @GetMapping("/login")
  @ApiOperation(value = "用户登录", notes = "用户登录")
  public WpUsers login(Long id) {
    WpUsers wpUsers = wpUsersService.selectByPrimaryKey(id);
    return wpUsers;
  }

}
