package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("推荐表")
public class Recommend extends BaseObject {

  /**
   * 推荐文章id
   */
  @ApiModelProperty("推荐文章id")
  private String articleId;

  /**
   * 推荐用户id
   */
  @ApiModelProperty("推荐用户id")
  private String userId;

  /**
   * 推荐类型[0文章，1用户]
   */
  @ApiModelProperty("推荐类型[0文章，1用户]")
  private String type;


}