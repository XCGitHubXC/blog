package com.graduation.blog.enums;

/**
 * @Description:状态枚举类型
 *
 */
public enum StatusEnum {

  USED("0", "使用"), DELETE("1", "删除");

  private String code;
  private String name;

  StatusEnum(String code, String name) {
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
