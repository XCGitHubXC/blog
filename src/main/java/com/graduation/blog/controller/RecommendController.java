package com.graduation.blog.controller;

import com.graduation.blog.domain.dto.responsedto.RecomBlogResponseDTO;
import com.graduation.blog.service.RecommendService;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  /**
   * 推荐用户
   */
  @ApiOperation(value = "推荐用户", notes = "推荐用户")
  @RequestMapping(value = "/recomUser", method = RequestMethod.GET)
  public Result recomUser() {




    return Result.success();
  }


  /**
   * 推荐博文
   */
  @ApiOperation(value = "推荐博文", notes = "推荐博文")
  @RequestMapping(value = "/recomBlog", method = RequestMethod.GET)
  public Result recomBlog() {
    List<RecomBlogResponseDTO> recomBlogResponseDTOS = recommendService.recomBlogs();
    return Result.success(recomBlogResponseDTOS);
  }


  /**
   * 类别博文推荐
   */
  @ApiOperation(value = "类别博文推荐", notes = "类别博文推荐")
  @RequestMapping(value = "/recomTypeBlog/{articleType}", method = RequestMethod.GET)
  public Result recomTypeBlog(@PathVariable String articleType) {
    List<RecomBlogResponseDTO> recomBlogResponseDTOS =
        recommendService.recomTypeBlogs(articleType);
    return Result.success(recomBlogResponseDTOS);
  }




}
