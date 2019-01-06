package com.graduation.blog.domain;

import java.util.Date;
import lombok.Data;

@Data
public class WpUsers {

  private Long id;

  private String userLogin;

  private String userPass;

  private String userNicename;

  private String userEmail;

  private String userUrl;

  private Date userRegistered;

  private String userActivationKey;

  private Integer userStatus;

  private String displayName;


}