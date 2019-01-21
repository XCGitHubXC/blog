package com.graduation.blog.domain;

import lombok.Data;

@Data
public class Comment extends BaseObject {

  private String pId;

  private String articleId;

  private String userId;

  private String content;


}