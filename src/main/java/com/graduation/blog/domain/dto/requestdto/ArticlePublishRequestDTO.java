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
@ApiModel("发表博文请求dto")
@Data
public class ArticlePublishRequestDTO implements Serializable {


  private static final long serialVersionUID = -6211541878049900288L;



  /**
   * 标题
   */
  @ApiModelProperty(value = "标题")
  @NotBlank(message = "标题不能为空")
  private String title;

  /**
   * 内容
   */
  @ApiModelProperty(value = "内容")
  @NotBlank(message = "内容不能为空")
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
  @NotBlank(message = "文章类型不能为空[原创(0)，转载(1)，翻译(2)]")
  private String articleType;

  /**
   * 分类
   */
  @ApiModelProperty(value = "分类")
  @NotBlank(message = "文章分类不能为空")
  private String type;


}
