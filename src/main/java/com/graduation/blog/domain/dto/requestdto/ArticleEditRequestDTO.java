package com.graduation.blog.domain.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 发表博文请求dto
 *
 */
@ApiModel("编辑博文请求dto")
@Data
public class ArticleEditRequestDTO implements Serializable {


  private static final long serialVersionUID = -997809943520811168L;

  /**
   * articleId 博文id
   */
  @ApiModelProperty(value = "articleId 博文id", required = true)
  @NotBlank(message = "articleId不能为空")
  private String articleId;


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
  @ApiModelProperty(value = "文章类型[原创(0)，转载(1)，翻译(2)]")
  private String articleType;

  /**
   * 分类
   */
  @ApiModelProperty(value = "分类[请查询字典表]")
  private String type;


}
