package com.graduation.blog.controller;

import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.domain.dto.requestdto.AuditBlogRequestDTO;
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
   *  发表博文[待审核]
   */
  @ApiOperation(value = "发表博文[待审核]", notes = "发表博文[待审核]")
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


}