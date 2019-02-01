package com.graduation.blog.domain.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: xiachuan
 * @Date: 2019/2/1
 * @Description: 评论文章请求DTO
 */
@ApiModel("评论文章请求DTO")
@Data
public class CommentBlogRequestDTO implements Serializable {

  private static final long serialVersionUID = 6965393295108543564L;

  /**
   * 文章id
   */
  @ApiModelProperty(value = "文章id", required = true)
  private String articleId;


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
