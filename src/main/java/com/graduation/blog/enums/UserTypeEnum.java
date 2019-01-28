package com.graduation.blog.enums;

/**
 * @Author: xiachuan
 * @Date: 2019/1/28
 * @Description: 用户类型
 */
public enum UserTypeEnum {

  // 用户类型
  ORDINARY("0", "普通用户"), ADMIN("1", "管理员");

  private String code;
  private String name;

  UserTypeEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
