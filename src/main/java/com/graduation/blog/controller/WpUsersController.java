package com.graduation.blog.controller;

import com.graduation.blog.domain.WpUsers;
import com.graduation.blog.service.WpUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@RestController
public class WpUsersController {

  @Autowired
  private WpUsersService wpUsersService;

  @GetMapping("/select/{id}")
  public WpUsers selectByPrimKey(@PathVariable("id") Long id) {
    WpUsers wpUsers = wpUsersService.selectByPrimaryKey(id);
    return wpUsers;
  }


}
