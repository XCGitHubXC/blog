package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "附件基本信息")
public class FileInfo extends BaseObject {

  /**
   * 文件路径
   */
  @ApiModelProperty(value = "文件路径")
  private String filePath;

  /**
   * 文件初始名
   */
  @ApiModelProperty(value = "文件初始名")
  private String originFileName;

  /**
   * 服务器上的文件名
   */
  @ApiModelProperty(value = "服务器上的文件名")
  private String remoteFileName;

  /**
   * 平台来源
   */
  @ApiModelProperty(value = "平台来源")
  private String platformSource;

  /**
   * 文件大小
   */
  @ApiModelProperty(value = "文件大小")
  private String fileSize;
  /**
   * 远程路径
   */
  @ApiModelProperty(value = "远程路径")
  private String fileUrl;

}