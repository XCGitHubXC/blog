package com.graduation.blog.controller;

import com.graduation.blog.service.StoreService;
import com.graduation.blog.utils.ContextUtil;
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
@RequestMapping("/user/store")
@Api(value = "收藏模块", tags = "收藏模块")
public class StoreController {

  @Autowired
  private StoreService storeService;

  /**
   * 收藏文章
   */
  @ApiOperation(value = "收藏文章", notes = "收藏文章")
  @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
  public Result store(@PathVariable String articleId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    storeService.storeBlog(currentUserId, articleId);
    return Result.success();
  }


  /**
   * 取消收藏文章
   */
  @ApiOperation(value = "取消收藏文章", notes = "取消收藏文章")
  @RequestMapping(value = "/cancel/{articleId}", method = RequestMethod.GET)
  public Result cancelArticle(@PathVariable String articleId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    storeService.cancelStoreBlog(currentUserId, articleId);
    return Result.success();
  }


  /**
   * 我的收藏
   */
  @ApiOperation(value = "我的收藏", notes = "我的收藏")
  @RequestMapping(value = "/myStore", method = RequestMethod.GET)
  public Result myStore() {
    String currentUserId = ContextUtil.getCurrentUserId();
    List<String> strings = storeService.myStores(currentUserId);
    return Result.success(strings);
  }


}
