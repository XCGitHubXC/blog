package com.graduation.blog.security;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = 689177934199815232L;
  private String userId;
  private String mobileNo;
  private String email;

  private String jwtToken;

  public JWTAuthenticationToken(String jwtToken) {
    super(null);
    this.jwtToken = jwtToken;
    setAuthenticated(false);
  }

  public JWTAuthenticationToken(String userId, String mobileNo, String email,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.userId = userId;
    this.mobileNo = mobileNo;
    this.email = email;
    super.setAuthenticated(true);
  }

  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    if (isAuthenticated) {
      throw new IllegalArgumentException(
          "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    }

    super.setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return mobileNo;
  }

  public String getUserId() {
    return userId;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public String getEmail() {
    return email;
  }

  public String getJwtToken() {
    return jwtToken;
  }
}
