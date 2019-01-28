package com.graduation.blog.controller;

import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.service.ArticleService;
import com.graduation.blog.utils.ContextUtil;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "文章模块", tags = "文章模块")
public class ArticleController {

  @Autowired
  private ArticleService articleService;


  /**
   *  发表博文[待审核]
   */
  @ApiOperation(value = "发表博文[待审核]", notes = "发表博文[待审核]")
  @RequestMapping(value = "publish", method = RequestMethod.POST)
  public Result userLogin(@RequestBody @Valid ArticlePublishRequestDTO dto) {
    String currentUserId = ContextUtil.getCurrentUserId();
    articleService.publishBlog(currentUserId, dto);
    return Result.success();
  }


}
