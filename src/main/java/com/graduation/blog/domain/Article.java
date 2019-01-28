package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Author :xiachuan
 * @Date :2019/1/21
 * @Description : 博文表
 */
@Data
@ApiModel("博文表")
public class Article extends BaseObject {

  /**
   * 用户id
   */
  @ApiModelProperty(value = "用户id")
  private String userId;

  /**
   * 标题
   */
  @ApiModelProperty(value = "标题")
  private String title;

  /**
   * 内容
   */
  @ApiModelProperty(value = "内容")
  private String content;

  /**
   * 文件id
   */
  @ApiModelProperty(value = "文件id")
  private String fileId;

  /**
   * 文章类型
   */
  @ApiModelProperty(value = "文章类型")
  private String articleType;

  /**
   * 分类
   */
  @ApiModelProperty(value = "分类")
  private String type;

  /**
   * 阅读数
   */
  @ApiModelProperty(value = "阅读数")
  private String readNum;


  /**
   * 审核[0不通过, 1通过]
   */
  @ApiModelProperty(value = "审核[0不通过, 1通过, 2待审核]")
  private String audit;
}