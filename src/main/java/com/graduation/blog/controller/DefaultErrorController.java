package com.graduation.blog.controller;


import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Result;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class DefaultErrorController implements ErrorController {

  public static final String ERROR_PATH = "/error";

  @RequestMapping(ERROR_PATH)
  @ResponseBody
  public Result handleError(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String message = (String) request.getAttribute("javax.servlet.error.message");
    String requestUrl = (String) request.getAttribute("javax.servlet.error.request_uri");
    Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

    log.error("error url :{} , http_status : {} , error_message : {} , exception {} ", requestUrl,
        statusCode, message, exception);
    if (exception != null) {
      return Result.failed(exception);
    } else {
      StringBuilder sb = new StringBuilder();
      if (statusCode != null) {
        sb.append(statusCode.toString());
      }
      if (StringUtils.isNotBlank(message)) {
        sb.append(",").append(message);
      }
      return Result.failed(ErrorCode.UNEXCEPTED, sb.toString());
    }
  }

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }
}
