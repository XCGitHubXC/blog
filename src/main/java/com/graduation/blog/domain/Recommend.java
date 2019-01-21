package com.graduation.blog.domain;

import lombok.Data;

@Data
public class Recommend extends BaseObject {

  private String articleId;

  private String userId;


}