package com.graduation.blog.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Author :xiachuan
 * @Date :2019/1/21
 * @Description : 评论表
 */
@Data
public class Comment extends BaseObject {

  /**
   * 父id
   */
  @ApiModelProperty(value = "父id")
  private String pId;

  /**
   * 文章id
   */
  @ApiModelProperty(value = "文章id")
  private String articleId;

  /**
   * 评论者id
   */
  @ApiModelProperty(value = "评论者id")
  private String userId;

  /**
   * 评论内容
   */
  @ApiModelProperty(value = "评论内容")
  private String content;


}