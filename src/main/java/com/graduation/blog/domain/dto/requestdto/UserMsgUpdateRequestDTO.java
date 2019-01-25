package com.graduation.blog.domain.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户信息修改dto
 *
 */
@ApiModel("用户信息修改dto")
@Data
public class UserMsgUpdateRequestDTO implements Serializable {


  private static final long serialVersionUID = 1834411792301388662L;


  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称", required = true)
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
   * 职业
   */
  @ApiModelProperty(value = "职业")
  private String profession;


  /**
   * 地区
   */
  @ApiModelProperty(value = "地区")
  private String region;


}
