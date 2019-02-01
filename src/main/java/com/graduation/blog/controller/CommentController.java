package com.graduation.blog.controller;

import com.graduation.blog.domain.Comment;
import com.graduation.blog.domain.dto.requestdto.CommentBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ReplyCommRequestDTO;
import com.graduation.blog.service.CommentService;
import com.graduation.blog.utils.ContextUtil;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/29
 * @Description:
 **/
@RestController
@RequestMapping("/comment")
@Api(value = "评论模块", tags = "评论模块")
public class CommentController {

  @Autowired
  private CommentService commentService;


  /**
   *  评论文章
   */
  @ApiOperation(value = "评论文章", notes = "评论文章")
  @RequestMapping(value = "/commentArticle", method = RequestMethod.POST)
  public Result commentArticle(@RequestBody CommentBlogRequestDTO cbDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    commentService.commentArticle(currentUserId, cbDTO);
    return Result.success();
  }

  /**
   *  回复评论
   */
  @ApiOperation(value = "回复评论", notes = "回复评论")
  @RequestMapping(value = "/replyComm", method = RequestMethod.POST)
  public Result replyComm(@RequestBody ReplyCommRequestDTO rcDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    commentService.replyComm(currentUserId, rcDTO);
    return Result.success();
  }


  /**
   *  查询文章的评论
   */
  @ApiOperation(value = "查询文章的评论", notes = "查询文章的评论")
  @RequestMapping(value = "/selectComm/{articleId}", method = RequestMethod.GET)
  public Result<List<Comment>> selectComms(@PathVariable String articleId) {
    List<Comment> comments = commentService.selectComms(articleId);
    return Result.success(comments);
  }

  /**
   *  查看回复
   */
  @ApiOperation(value = "查看回复", notes = "查看回复")
  @RequestMapping(value = "/selectReply/{commId}", method = RequestMethod.GET)
  public Result<List<Comment>> selectReply(@PathVariable String commId) {
    List<Comment> comments = commentService.selectReply(commId);
    return Result.success(comments);
  }



}
