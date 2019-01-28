package com.graduation.blog.enums;

/**
 * @Author :xiachuan
 * @Date :2019/1/25
 * @Description : 博文审核分类枚举
 */
public enum AuditClassEnum {

  // 博文审核分类枚举
  NOT_PASS("0", "不通过"), PASS("1", "通过"), WAIT_AUDIT("2", "待审核");

  private String code;
  private String name;

  AuditClassEnum(String code, String name) {
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
