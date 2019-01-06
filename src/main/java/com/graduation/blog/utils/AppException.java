package com.graduation.blog.utils;

import org.apache.commons.lang3.StringUtils;

public class AppException extends RuntimeException {

  private ErrorCode code;

  public AppException(ErrorCode code) {
    this.code = code;
  }

  public AppException(ErrorCode code, String message) {
    super(message);
    this.code = code;
  }

  public ErrorCode getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    if (StringUtils.isBlank(super.getMessage())) {
      return code.toString();
    }
    return super.getMessage();
  }
}
