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
@RequestMapping("/blog/recommend")
@Api(value = "博文推荐", tags = "博文推荐")
public class RecommendController {

  @Autowired
  private RecommendService recommendService;

 


}
