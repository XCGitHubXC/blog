package com.graduation.blog.domain;

import lombok.Data;

@Data
public class Article extends BaseObject {

  private String userId;

  private String title;

  private String content;

  private String fileId;

  private String articleType;

  private String type;

  private String readNum;


}