package com.graduation.blog.domain.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: xiachuan
 * @Date: 2019/2/1
 * @Description: 回复评论请求DTO
 */
@ApiModel("回复评论请求DTO")
@Data
public class ReplyCommRequestDTO implements Serializable {


  private static final long serialVersionUID = -8323453504836308734L;
  /**
   * 文章id
   */
  @ApiModelProperty(value = "文章id", required = true)
  private String articleId;

  /**
   * 评论id(父id)
   */
  @ApiModelProperty(value = "评论id(父id)", required = true)
  private String commId;


  /**
   * 用户id
   */
  @ApiModelProperty(value = "用户id")
  private String userId;

  /**
   * 评论内容
   */
  @ApiModelProperty(value = "评论内容", required = true)
  private String content;


}
