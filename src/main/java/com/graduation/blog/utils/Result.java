package com.graduation.blog.utils;


public class Result<T> {

  private int code;
  private T data;
  private String message;

  public Result() {

  }

  private Result(T data) {
    this.code = ErrorCode.SUCCESS.getValue();
    this.data = data;
  }

  private Result(ErrorCode code, String message) {
    ValidateMessageParser vm = SpringContextUtil.getBean(ValidateMessageParser.class);
    if (vm != null) {
      message = vm.parseMessage(code, message);
    }
    this.code = code.getValue();
    this.message = message;
  }

  private Result(ErrorCode code, String message, T data) {
    ValidateMessageParser vm = SpringContextUtil.getBean(ValidateMessageParser.class);
    if (vm != null) {
      message = vm.parseMessage(code, message);
    }
    this.code = code.getValue();
    this.message = message;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static Result success() {
    return new Result(null);
  }

  /**
   * 解决范型问题，无法展示data基础类型
   */
  public static <U> Result<U> success(U data) {
    return new Result(data);
  }

  public static Result failed(Exception e) {
    if (e instanceof AppException) {
      return fail((AppException) e);
    } else {
      return new Result(ErrorCode.UNEXCEPTED, null);
    }
  }

  private static Result fail(AppException e) {
    return new Result(e.getCode(), e.getMessage());
  }

  public static Result failed(ErrorCode errorCode, String message) {
    return new Result(errorCode, message);

  }

  public static <U> Result<U> failed(ErrorCode errorCode, String message, U data) {
    return new Result(errorCode, message, data);

  }

  public static <U> Result<U> success(ErrorCode errorCode, String message, U data) {
    return new Result(errorCode, message, data);
  }
}
