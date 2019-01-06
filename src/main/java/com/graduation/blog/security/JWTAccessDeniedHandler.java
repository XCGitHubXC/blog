package com.graduation.blog.security;

import com.alibaba.fastjson.JSONObject;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Result;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.error("accessDenied  path : {}", request.getServletPath());

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter()
        .write(JSONObject.toJSONString(Result.failed(new AppException(ErrorCode.ACCESS_DENIED))));
    response.flushBuffer();
  }
}
