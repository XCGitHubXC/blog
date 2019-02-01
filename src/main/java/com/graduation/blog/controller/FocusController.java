package com.graduation.blog.controller;

import com.graduation.blog.service.FocusService;
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
@RequestMapping("/user/focus")
@Api(value = "关注模块", tags = "关注模块")
public class FocusController {

  @Autowired
  private FocusService focusService;


  /**
   * 关注用户
   */
  @ApiOperation(value = "关注用户", notes = "关注用户")
  @RequestMapping(value = "/{focusUserId}", method = RequestMethod.GET)
  public Result focus(@PathVariable String focusUserId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    focusService.focusUser(currentUserId, focusUserId);
    return Result.success();
  }


  /**
   * 取消关注
   */
  @ApiOperation(value = "取消关注", notes = "取消关注")
  @RequestMapping(value = "/cancel/{focusUserId}", method = RequestMethod.GET)
  public Result cancelFocus(@PathVariable String focusUserId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    focusService.cancelFocusUser(currentUserId, focusUserId);
    return Result.success();
  }


  /**
   * 我的关注用户
   */
  @ApiOperation(value = "我的关注用户", notes = "我的关注用户")
  @RequestMapping(value = "/myFocus", method = RequestMethod.GET)
  public Result<List<String>> myFocus() {
    String currentUserId = ContextUtil.getCurrentUserId();
    List<String> focusIds = focusService.myFocus(currentUserId);
    return Result.success(focusIds);
  }


  /**
   * 我的粉丝
   */
  @ApiOperation(value = "我的粉丝", notes = "我的粉丝")
  @RequestMapping(value = "/myFans", method = RequestMethod.GET)
  public Result<List<String>> myFans() {
    String currentUserId = ContextUtil.getCurrentUserId();
    List<String> focusIds = focusService.myFans(currentUserId);
    return Result.success(focusIds);
  }

}
