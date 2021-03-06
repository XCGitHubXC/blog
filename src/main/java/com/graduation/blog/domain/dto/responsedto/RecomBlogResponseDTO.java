package com.graduation.blog.domain.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: xiachuan
 * @Date: 2019/3/6
 * @Description:
 */
@Data
@ApiModel(value = "推荐博文返回DTO")
public class RecomBlogResponseDTO implements Serializable {

  private static final long serialVersionUID = 6536796578192646294L;


  /**
   * 自增
   */
  @ApiModelProperty(value = "id")
  private String id;


  /**
   * 用户id
   */
  @ApiModelProperty(value = "用户id")
  private String userId;
  /**
   * 博文标题
   */
  @ApiModelProperty(value = "博文标题")
  private String title;

  /**
   * 博文内容
   */
  @ApiModelProperty(value = "博文内容")
  private String content;

  /**
   * 阅读数量
   */
  @ApiModelProperty(value = "阅读数量")
  private String readNum;

  /**
   * 评论数量
   */
  @ApiModelProperty(value = "评论数量")
  private String commentNum;

  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  /**
   * 头像图片
   */
  @ApiModelProperty(value = "头像图片")
  private String fileId;

  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称")
  private String nickName;


  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }


}
