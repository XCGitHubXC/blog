package com.graduation.blog.domain;

import lombok.Data;

@Data
public class FileInfo extends BaseObject {

  private String filePath;

  private String originFileName;

  private String remoteFileName;

  private String platformSource;

  private String fileSize;

  private String fileUrl;


}