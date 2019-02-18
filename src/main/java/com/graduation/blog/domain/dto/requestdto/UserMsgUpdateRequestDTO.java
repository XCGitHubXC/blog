package com.graduation.blog.domain.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户信息修改dto
 *
 */
@ApiModel("用户信息修改dto")
@Data
public class UserMsgUpdateRequestDTO implements Serializable {


  private static final long serialVersionUID = 1834411792301388662L;


  /**
   * 姓名
   */
  @ApiModelProperty(value = "姓名")
  private String name;

  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称")
  private String nickName;

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
   * 职业
   */
  @ApiModelProperty(value = "职业")
  private String profession;


  /**
   * 地区
   */
  @ApiModelProperty(value = "地区")
  private String region;

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

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
}
