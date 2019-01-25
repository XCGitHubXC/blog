package com.graduation.blog.domain.dto.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@ApiModel("上传文件返回")
public class FileUploadResponseDTO implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * 文件ID
   */
  @ApiModelProperty(value = "附件id")
  private String fileId;
  /**
   * 文件存放路径
   */
  @ApiModelProperty(value = "文件访问路径")
  private String fileUrl;
  /**
   * 文件原始名称
   */
  @ApiModelProperty(value = "文件原始名称")
  private String originFileName;
  /**
   *  远程文件名
   */
  @ApiModelProperty(value = "远程文件名")
  private String remoteFileName;
}
