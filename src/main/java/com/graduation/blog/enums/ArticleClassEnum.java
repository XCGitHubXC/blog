package com.graduation.blog.enums;

/**
 * @Author :xiachuan
 * @Date :2019/1/25
 * @Description : 博文分类枚举
 */
public enum ArticleClassEnum {

  // 博文分类枚举
  INTELLIGENCE("0", "人工智能"), MOBILEDEV("1", "移动开发"), INTERTHINGS("2", "物联网"),
  FRAMEWORK("3", "架构"), CLOUDCOMP("4", "云计算/大数据"), INTERNET("5", "互联网"),
  GAME("6", "游戏开发"), OPERATION("7", "运维"), DATABASE("8", "数据库"),
  FRONTEND("9", "前端"), BACKEND("10", "后端"), LANGUAGE("11", "编程语言"),
  RESEARCH("12", "研发管理"), SECURITY("13", "安全"), PROLIFE("14", "程序人生"),
  BLOCKCHAIN("15", "区块链"), AUDIO("16", "音视频开发"), INFORMATION("17", "资讯"),
  COMPUTER("18", "计算机理论基础");

  private String code;
  private String name;

  ArticleClassEnum(String code, String name) {
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
