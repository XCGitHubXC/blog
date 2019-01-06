package com.graduation.blog.utils;


import com.google.common.base.Strings;
import java.util.List;

public class Assert {
  public static void isNotNull(Object obj, ErrorCode errorCode) {
    isNotNull(obj, errorCode, null);
  }

  public static void isNotNull(Object obj, ErrorCode errorCode, String message) throws AppException {
    if (obj == null) {
      throw new AppException(errorCode, message);
    }
  }

  public static void isNull(Object obj, ErrorCode errorCode) {
    isNull(obj, errorCode, null);
  }

  public static void isNull(Object obj, ErrorCode errorCode, String message) throws AppException {
    if (obj != null) {
      throw new AppException(errorCode, message);
    }
  }

  public static void isTrue(boolean condition, ErrorCode errorCode) {
    isTrue(condition, errorCode, null);
  }

  public static void isTrue(boolean condition, ErrorCode errorCode, String message)
      throws AppException {
    if (!condition) {
      throw new AppException(errorCode, message);
    }
  }

  public static void isEquals(Object obj1, Object obj2, ErrorCode code) throws AppException {
    isEquals(obj1, obj2, code, null);
  }

  public static void isEquals(Object obj1, Object obj2, ErrorCode code, String message)
      throws AppException {
    if (obj1 == obj2) {
      return;
    }
    if (obj1 != null && obj1.equals(obj2)) {
      return;
    }
    throw new AppException(code, message);
  }

  public static void isNotEmpty(String str, ErrorCode code) {
    isNotEmpty(str, code, null);
  }

  public static void isNotEmpty(String str, ErrorCode code, String message) {
    if (Strings.isNullOrEmpty(str)) {
      throw new AppException(code, message);
    }
  }

  public static void isNumeric(String str, ErrorCode code) {
    isNumeric(str, code, null);
  }

  /**
   * <pre>
   * 如果非空，抛异常
   * @param list
   * @param code
   * @param message
   */
  public static void isListEmpty(List<?> list, ErrorCode code, String message) {
    if (list != null && list.size() > 0) {
      throw new AppException(code, message);
    }
  }

  /**
   * <pre>
   * 如果非空，抛异常
   * @param list
   * @param code
     */
  public static void isListEmpty(List<?> list, ErrorCode code) {
    isListEmpty(list, code, null);
  }

  /**
   * <pre>
   * 如果空，抛异常
   * @param list
   * @param code
   * @param message
   */
  public static void isListNotEmpty(List<?> list, ErrorCode code, String message) {
    if (list == null || list.isEmpty()) {
      throw new AppException(code, message);
    }
  }

  /**
   * <pre>
   * 如果空，抛异常
   * @param list
   * @param code
   */
  public static void isListNotEmpty(List<?> list, ErrorCode code) {
    isListNotEmpty(list, code, null);
  }

  /**
   * str必须是自然数和0
   * 
   * @param str 非负整数
   * @param code
   */
  public static void isNumeric(String str, ErrorCode code, String message) {
    if (Strings.isNullOrEmpty(str) || !CommonsUtils.isNumeric(str)) {
      throw new AppException(code, message);
    }
  }
}
