package com.graduation.blog.domain.dto.responsedto;

import com.graduation.blog.domain.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: xiachuan
 * @Date: 2019/3/6
 * @Description:
 */
@Data
@ApiModel(value = "查询单个博文返回DTO")
public class SelectBlogResponseDTO extends Article implements Serializable {


  private static final long serialVersionUID = 3896187840460656822L;

  /**
   * 评论数量
   */
  @ApiModelProperty(value = "评论数量")
  private String commentNum;



  /**
   * 该博文是否可以点赞
   */
  @ApiModelProperty(value = "该博文是否可以点赞[1:已经点过赞了，0:未点赞]")
  private String fabulousFlag;



}
