package com.graduation.blog.utils;

import com.graduation.blog.domain.User;
import com.graduation.blog.security.JWTAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {

  public static String getCurrentUserId() {
    return getJWTAuthentication().getUserId();
  }

  public static String getCurrentMobileNo() {
    return getJWTAuthentication().getMobileNo();
  }

  public static String getCurrentEmail() {
    return getJWTAuthentication().getEmail();
  }

  public static JWTAuthenticationToken getJWTAuthentication() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Assert.isTrue(authentication != null && authentication.isAuthenticated()
        && authentication instanceof JWTAuthenticationToken, ErrorCode.USER_NOT_LOGIN);

    return (JWTAuthenticationToken) authentication;
  }

  public static User getLoginUserInfo() {
    JWTAuthenticationToken userJWT = getJWTAuthentication();
    User loginUser = new User();
    loginUser.setId(userJWT.getUserId());
    loginUser.setMobileNo(userJWT.getMobileNo());
    loginUser.setEmail(userJWT.getEmail());
    return loginUser;
  }
}
