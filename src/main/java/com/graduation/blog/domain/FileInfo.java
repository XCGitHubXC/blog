package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("文件表")
public class FileInfo extends BaseObject {

  private String filePath;

  private String originFileName;

  private String remoteFileName;

  private String platformSource;

  private String fileSize;

  private String fileUrl;


}