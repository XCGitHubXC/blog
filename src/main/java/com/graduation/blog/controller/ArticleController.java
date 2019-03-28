package com.graduation.blog.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.dto.requestdto.ArticleEditRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.domain.dto.requestdto.AuditBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.BlogsQueryRequestDTO;
import com.graduation.blog.service.ArticleService;
import com.graduation.blog.utils.ContextUtil;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/article")
@Api(value = "博文模块", tags = "博文模块")
public class ArticleController {

  @Autowired
  private ArticleService articleService;


  /**
   *  发表博文
   */
  @ApiOperation(value = "发表博文", notes = "发表博文")
  @RequestMapping(value = "publish", method = RequestMethod.POST)
  public Result publish(@RequestBody @Valid ArticlePublishRequestDTO dto) {
    String currentUserId = ContextUtil.getCurrentUserId();
    articleService.publishBlog(currentUserId, dto);
    return Result.success();
  }


  /**
   *  审核博文
   */
  @ApiOperation(value = "审核博文", notes = "审核博文")
  @RequestMapping(value = "/auditBlog", method = RequestMethod.POST)
  public Result audit(@RequestBody AuditBlogRequestDTO abRequestDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    articleService.auditBlog(currentUserId, abRequestDTO);
    return Result.success();
  }


  /**
   *  删除博文
   */
  @ApiOperation(value = "删除博文", notes = "删除博文")
  @RequestMapping(value = "/deleteArticle/{articleId}", method = RequestMethod.DELETE)
  public Result deleteArticle(@PathVariable String articleId) {
    articleService.deleteBlog(articleId);
    return Result.success();
  }



  /**
   *  博文查询
   */
  @ApiOperation(value = "博文查询", notes = "博文查询")
  @RequestMapping(value = "/selectBlog/{articleId}", method = RequestMethod.GET)
  public Result<Article> selectBlog(@PathVariable String articleId) {
    Article article = articleService.selectBlog(articleId);
    return Result.success(article);
  }


  /**
   *  我的博文列表
   */
  @ApiOperation(value = "用户博文列表", notes = "用户博文列表")
  @RequestMapping(value = "/listBlog", method = RequestMethod.POST)
  public Result<PageInfo<Article>> listBlog(@RequestBody BlogsQueryRequestDTO bqRquestDTO) {
    PageInfo<Article> articlePageInfo = new PageInfo<>();
    if (Strings.isNullOrEmpty(bqRquestDTO.getUserId())) {
      String currentUserId = ContextUtil.getCurrentUserId();
      articlePageInfo = articleService.myBlogList(currentUserId, bqRquestDTO);
    } else {
      articlePageInfo = articleService.myBlogList(bqRquestDTO.getUserId(), bqRquestDTO);
    }
    return Result.success(articlePageInfo);
  }

  /**
   *  博文点赞
   */
  @ApiOperation(value = "博文点赞", notes = "博文点赞")
  @RequestMapping(value = "/fabulousBlog/{articleId}", method = RequestMethod.GET)
  public Result fabulousBlog(@PathVariable String articleId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    articleService.fabulousBlog(currentUserId, articleId);
    return Result.success();
  }


  /**
   *  取消点赞
   */
  @ApiOperation(value = "取消点赞", notes = "取消点赞")
  @RequestMapping(value = "/cancleFabulous/{articleId}", method = RequestMethod.GET)
  public Result cancleFabulous(@PathVariable String articleId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    articleService.cancleFabulous(currentUserId, articleId);
    return Result.success();
  }

  /**
   *  博文编辑
   */
  @ApiOperation(value = "博文编辑", notes = "博文编辑")
  @RequestMapping(value = "/editBlog", method = RequestMethod.POST)
  public Result editBlog(@RequestBody ArticleEditRequestDTO aeRequestDTO) {
    articleService.editBlog(aeRequestDTO);
    return Result.success();
  }



}
