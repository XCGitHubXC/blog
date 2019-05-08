package com.graduation.blog.domain.dto.responsedto;

import com.graduation.blog.domain.Comment;
import com.graduation.blog.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: xiachuan
 * @Date: 2019/5/8
 * @Description:
 */
@Data
@ApiModel("我的博文列表返回对象")
public class QueryCommResponseDTO implements Serializable {

  private static final long serialVersionUID = 673327273344359709L;

  /**
   * 评论内容相关信息
   */
  @ApiModelProperty(value = "评论内容相关信息")
  private Comment comment;


  /**
   * 评论人相关信息
   */
  @ApiModelProperty(value = "评论人相关信息")
  private User user;



  /**
   * 该条评论的回复数
   */
  @ApiModelProperty(value = "该条评论的回复数")
  private Integer replyCount;




}
