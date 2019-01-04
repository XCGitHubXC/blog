package com.graduation.blog.controller;

import com.graduation.blog.domain.WpUsers;
import com.graduation.blog.service.WpUsersService;
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
@RequestMapping("/wpUsers")
@Api(value = "用户查询", tags = "用户查询")
public class WpUsersController {

  @Autowired
  private WpUsersService wpUsersService;

  @GetMapping("/select/{id}")
  @ApiOperation(value = "查询用户", notes = "查询用户")
  public WpUsers selectByPrimKey(@PathVariable("id") Long id) {
    WpUsers wpUsers = wpUsersService.selectByPrimaryKey(id);
    return wpUsers;
  }


}
