package com.graduation.blog.domain.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 审核文章请求dto
 *
 *
 */
@ApiModel("审核文章请求dto")
@Data
public class AuditBlogsRequestDTO implements Serializable {


  private static final long serialVersionUID = -3643060081212670482L;

  /**
   * 文章id
   */
  @ApiModelProperty(value = "文章id", required = true)
  @NotBlank(message = "博文id不能为空")
  private List<String> articleId;

  /**
   * 审核意见
   */
  @ApiModelProperty(value = "审核意见", required = true)
  @NotBlank(message = "审核意见不能为空")
  private String auditStr;

}
