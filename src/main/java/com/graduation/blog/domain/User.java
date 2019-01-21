package com.graduation.blog.domain;

import lombok.Data;

@Data
public class User extends BaseObject {

  private String mobileNo;

  private String email;

  private String name;

  private String summary;

  private String signature;

  private String profession;

  private String region;

  private String password;

  private String authority;


}