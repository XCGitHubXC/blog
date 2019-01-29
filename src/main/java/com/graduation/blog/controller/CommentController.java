package com.graduation.blog.controller;

import com.graduation.blog.service.CommentService;
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
@RequestMapping("/user/comment")
@Api(value = "用户评论", tags = "用户评论")
public class CommentController {

  @Autowired
  private CommentService commentService;




}
