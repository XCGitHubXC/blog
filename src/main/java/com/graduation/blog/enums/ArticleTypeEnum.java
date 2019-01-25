package com.graduation.blog.enums;

/**
 * @Author :xiachuan
 * @Date :2019/1/25
 * @Description : 博文类型枚举
 */
public enum ArticleTypeEnum {

  // 博文类型枚举
  ORIGINAL("0", "原创"), REPRINT("1", "转载"), TRANSLATE("2", "翻译");

  private String code;
  private String name;

  ArticleTypeEnum(String code, String name) {
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
