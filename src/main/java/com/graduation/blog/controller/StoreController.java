package com.graduation.blog.controller;

import com.graduation.blog.service.StoreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/29
 * @Description:
 **/
@RestController
@RequestMapping("/user/store")
@Api(value = "用户收藏", tags = "用户收藏")
public class StoreController {

  @Autowired
  private StoreService storeService;




}
