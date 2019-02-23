package com.graduation.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

  /**
   * 头像图片id
   */
  @ApiModelProperty(value = "头像图片id")
  private String fileId;

  /**
   * 性别
   */
  @ApiModelProperty(value = "[(0 女生),(1 男生)]")
  private String gender;

  /**
   * 生日
   */
  @ApiModelProperty(value = "生日")
  private Date birthday;


  /**
   * 积分
   */
  @ApiModelProperty(value = "积分")
  private Integer score;


  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称")
  private String nickName;

  /**
   * 访问量
   */
  @ApiModelProperty(value = "访问量")
  private Integer pageView;


  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  public Date getBirthday() {
    return birthday;
  }


}