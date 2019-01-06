package com.graduation.blog.controller;


import com.graduation.blog.constants.ValidateMessage;
import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Result;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 *
 * 异常处理器
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class DefaultExceptionHandler {

  @ExceptionHandler(Exception.class)
  public Result processException(NativeWebRequest request, Exception e) {
    log.error("exception", e);
    return Result.failed(e);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public Result processMaxUploadSizeExceededException(NativeWebRequest request,
      MaxUploadSizeExceededException e) {
    log.error("uploadfile error", e);
    return Result.failed(ErrorCode.FILE_SIZE_OVERRUN, "文件大小超限!");
  }

  @ExceptionHandler(ValidationException.class)
  public Result processValidationException(ValidationException e) {
    log.error("validation error", e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, e.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public Result processConstraintViolationException(ConstraintViolationException e) {
    StringBuilder sb = new StringBuilder();
    e.getConstraintViolations().forEach(cv -> {
      sb.append(cv.getMessage());
    });
    log.error("validation error {} ", sb.toString(), e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, sb.toString());
  }

  @ExceptionHandler(BindException.class)
  public Result handleBindException(BindException e) {
    String message = e.getAllErrors().stream().map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining(", "));
    log.error("bind error {}", message, e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, message);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
    BindingResult br = e.getBindingResult();
    String message = br.getAllErrors().stream().map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining(", "));
    log.error("validation error {}", message, e);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, message);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    log.error(" error {}", ex);
    return Result.failed(ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.PARAMETER_IS_NULL);
  }
}
