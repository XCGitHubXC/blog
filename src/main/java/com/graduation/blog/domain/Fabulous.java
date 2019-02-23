package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("点赞表")
public class Fabulous extends BaseObject {


  /**
   * 博文id
   */
  @ApiModelProperty(value = "博文id")
  private String articleId;

  /**
   * 点赞人id
   */
  @ApiModelProperty(value = "点赞人id")
  private String userId;


}