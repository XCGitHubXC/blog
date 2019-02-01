package com.graduation.blog.controller;

import com.graduation.blog.service.RecommendService;
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
@RequestMapping("/recommend")
@Api(value = "推荐模块", tags = "推荐模块")
public class RecommendController {

  @Autowired
  private RecommendService recommendService;

 


}
