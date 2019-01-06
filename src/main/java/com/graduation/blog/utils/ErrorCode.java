package com.graduation.blog.utils;

/**
 * 错误码规则为四位纯数字 
 * 1.公共模块 
 * 2.登录模块 
 * 3.文件模块 
 * 4.后台管理模块
 * 
 * @author weibo
 *
 */
public enum ErrorCode {

  /**
   * 案件正在后台流转，不允许操作状态
   */
  CASE_IS_WORK(4000),

  /**
   * 案件操作不被允许
   */
  CASE_CHANGE_REFUSE(4001),

  /**
   * (保留码) 表示未知异常
   */
  UNEXCEPTED(9999),

  /* 公共模块错误码 */

  /**
   * 成功
   */
  SUCCESS(1000),
  /**
   * 用户未登录
   */
  USER_NOT_LOGIN(1001),
  /**
   * 认证令牌过期
   */
  AUTH_TOKEN_EXPIRE(1002),
  /**
   * 非法参数
   */
  ILLEGAL_PARAMETER(1003),
  /**
   * 权限不足
   */
  ACCESS_DENIED(1004),
  /**
   * 结果为空
   */
  RESULT_EMPTY(1005),

  /**
   * 操作数据库失败
   */
  FAIL_DATABASE(1006),


  /* 登录模块 */

  /**
   * 用户不存在
   */
  USER_NOT_EXISTS(2001),
  /**
   * 验证码不正确
   */
  SMS_CODE_INCORRECT(2002),
  /**
   * 用户名不存在
   */
  USER_NAME_NOT_EXISTS(2003),
  /**
   * 密码错误
   */
  PASSWORD_IS_WRONG(2004),
  /**
   * 手机验证码错误
   */
  PHONE_CODE_IS_WRONG(2005),
  /**
   * 邮箱验证码错误
   */
  EMAIL_CODE_IS_WRONG(2006),
  /**
   *  EXCEL解析异常
   */
  EXCEL_INVALIDATED(2007),
  
  /**
   * 用户类型为空
   */
  USER_TYPE_IS_EMPTY(2008),
  
  /**
   * 用户id为空
   */
  USER_ID_IS_EMPTY(2009),
  /**
   * 手机号为空
   */
  MOBILE_PHONE_IS_EMPTY(2010),
  
  /**
   * 字典类型
   */
  DICT_TYPE_IS_EMPTY(2011),
  


  /* 文件模块 */

  /**
   * 上传的文件大小超限
   */
  FILE_SIZE_OVERRUN(3001),
  /**
   * 空文件
   */
  EMPTY_FILE(3002),
  /**
   * 文件名为空
   */
  FILE_NAME_IS_EMPTY(3003),
  /**
   * 文件类型错误
   */
  FILE_TYPE_IS_WRONG(3005),

  /**
   * 文件处理失败
   */
  FAIL_FILE(3006),
  

  /* 后台管理模块 */
  
  /**
   * 手机号码已经存在
   */
  MOBILE_EXITS(4001),
  
  /**
   * 身份证和姓名不匹配
   */
  NAME_IDCARD_MISMATCHING(4002),
  /**
   * 字典码已经存在
   */
  DICTIONARY_CODE_EXITS(4003),
  /**
   * 文书模板ID为空
   */
  PAPER_ID_IS_EMPTY(4004),
  /**
   * 微信token结果为空
   */
  WEIXIN_TOKEN_EMPTY(4005),
  /**
   * 微信openId为空
   */
  WEIXIN_OPENID_EMPTY(4006),
  /**
   * 微信已经绑定
   */
  WEIXIN_IS_BIND(4007),
  /**
   * 微信未绑定
   */
  WEIXIN_IS_UNBIND(4008),
  /**
   * 微信未绑定该用户
   */
  WEIXIN_USER_MISMATCHING(4009),
  /**
   * 用户名已经存在
   */
  LOGIN_NAME_EXITS(4010),
  /**
   * 用户类型不匹配
   */
  USER_TYPE_MISMATCHING(4011),
  /**
   * 原手机号错误
   */
  MOBILE_PHONE_OLD_IS_WRONG(4012),

  /**
   * 审批流程重复
   */
  FLOW_EXISTS(5001),

  /**
   * 审批流程不岑在
   */
  FLOW_NOT_EXISTS(5001),
  /**
   * 不在操作时间中
   * */
  OUT_TIME_LIMIT(5002);

  private int value;

  private ErrorCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
