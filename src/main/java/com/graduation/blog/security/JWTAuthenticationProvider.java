package com.graduation.blog.security;


import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.ErrorCode;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

  @Resource
  private TokenGenerator tokenGenerator;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    JWTAuthenticationToken jwtAuthentication = (JWTAuthenticationToken) authentication;
    Assert.isNotNull(jwtAuthentication, ErrorCode.ILLEGAL_PARAMETER);
    String jwtToken = jwtAuthentication.getJwtToken();
    return tokenGenerator.generateAuthenticationToken(jwtToken);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JWTAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
