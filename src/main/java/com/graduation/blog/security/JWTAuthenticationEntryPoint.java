package com.graduation.blog.security;

import com.alibaba.fastjson.JSONObject;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Result;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter()
        .write(JSONObject.toJSONString(Result.failed(new AppException(ErrorCode.USER_NOT_LOGIN))));
    response.flushBuffer();
  }

}
