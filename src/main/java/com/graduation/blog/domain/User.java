package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Author :xiachuan
 * @Date :2019/1/21
 * @Description : 用户表
 */
@Data
@ApiModel("用户表")
public class User extends BaseObject {

  /**
   * 手机号
   */
  @ApiModelProperty(value = "手机号")
  private String mobileNo;

  /**
   * 邮箱
   */
  @ApiModelProperty(value = "邮箱")
  private String email;

  /**
   * 姓名
   */
  @ApiModelProperty(value = "姓名")
  private String name;

  /**
   * 简介
   */
  @ApiModelProperty(value = "简介")
  private String summary;

  /**
   * 签名
   */
  @ApiModelProperty(value = "签名")
  private String signature;

  /**
   * 行业
   */
  @ApiModelProperty(value = "行业")
  private String profession;

  /**
   * 地区
   */
  @ApiModelProperty(value = "地区")
  private String region;

  /**
   * 密码
   */
  @ApiModelProperty(value = "密码")
  private String password;

  /**
   * 权限
   */
  @ApiModelProperty(value = "权限")
  private String authority;


}