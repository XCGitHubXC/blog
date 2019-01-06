package com.graduation.blog.security;


import com.google.common.collect.Maps;
import com.graduation.blog.domain.dto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.LoginTokenResponseDTO;
import com.graduation.blog.domain.dto.RefreshTokenResponseDTO;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Java8DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenGenerator {

  @Value("${jwt.secret}")
  private String jwtSecret;
  @Value("${jwt.expireMinutes.authToken}")
  private int authTokenExpireMinutes;
  @Value("${jwt.expireMinutes.refreshToken}")
  private int refreshTokenExpireMinutes;

  /**
   * 基于当前登录用户的信息，生成完整登录令牌信息
   * 
   * @param loginInfo
   * @return
   */
  public LoginTokenResponseDTO generateLoginToken(LoginInfoResponseDTO loginInfo) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", loginInfo.getUserId());
    claims.put("userName", loginInfo.getUserName());
    claims.put("userType", loginInfo.getUserType());
    claims.put("roles", loginInfo.getRoles());

    Date authTokenExpireTime =
        Java8DateUtil.getDate(LocalDateTime.now().plusMinutes(authTokenExpireMinutes));

    Date refreshTokenExpireTime =
        Java8DateUtil.getDate(LocalDateTime.now().plusMinutes(refreshTokenExpireMinutes));

    String authToken = generateJWTToken(authTokenExpireTime, claims);

    String refreshToken = generateJWTToken(refreshTokenExpireTime, claims);

    LoginTokenResponseDTO token = new LoginTokenResponseDTO();
    token.setLoginInfo(loginInfo);
    token.setAuthToken(authToken);
    token.setRefreshToken(refreshToken);
    return token;
  }

  /**
   * 生成jwt token
   * 
   * @param expireTime 过期时间
   * @param claims 当前登录用户非敏感信息
   * @return
   */
  private String generateJWTToken(Date expireTime, Map<String, Object> claims) {
    return Jwts.builder().addClaims(claims).setExpiration(expireTime)
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  private Claims parseJWTToken(String jwtToken) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
  }

  /**
   * 认证令牌过期后用于生成新的认证令牌
   * 
   * @param refreshToken
   * @return
   */
  public RefreshTokenResponseDTO refreshToken(String refreshToken) {
    Claims claims = null;
    try {
      claims = parseJWTToken(refreshToken);
    } catch (Exception e) {
      log.error("Refresh Token failed refreshToken : {} ", refreshToken, e);
      throw new AppException(ErrorCode.USER_NOT_LOGIN);
    }
    Map<String, Object> newClaims = Maps.newHashMap();
    newClaims.put("userId", claims.get("userId"));
    newClaims.put("userName", claims.get("userName"));
    newClaims.put("roles", claims.get("roles"));

    Date authTokenExpireTime =
        Java8DateUtil.getDate(LocalDateTime.now().plusMinutes(authTokenExpireMinutes));

    Date refreshTokenExpireTime =
        Java8DateUtil.getDate(LocalDateTime.now().plusMinutes(refreshTokenExpireMinutes));

    String newAuthToken = generateJWTToken(authTokenExpireTime, newClaims);

    String newRefreshToken = generateJWTToken(refreshTokenExpireTime, newClaims);

    RefreshTokenResponseDTO refreshTokenDTO = new RefreshTokenResponseDTO();
    refreshTokenDTO.setAuthToken(newAuthToken);
    refreshTokenDTO.setRefreshToken(newRefreshToken);
    return refreshTokenDTO;
  }

  /**
   * 生成认证实体，基于spring security authenticationToken机制
   * 
   * @param authToken
   * @return
   */
  @SuppressWarnings("unchecked")
  public JWTAuthenticationToken generateAuthenticationToken(String authToken) {
    Claims claims = null;
    try {
      claims = parseJWTToken(authToken);
    } catch (ExpiredJwtException e) {
      log.error("AuthToken expire authToken: {} ", authToken);
      throw new AppException(ErrorCode.AUTH_TOKEN_EXPIRE);
    } catch (Exception e) {
      log.error("Auth failed, authToken : {}", authToken, e);
      throw new AppException(ErrorCode.USER_NOT_LOGIN);
    }
    String userId = String.valueOf(claims.get("userId"));
    String userName = String.valueOf(claims.get("userName"));
    List<String> roles = (List<String>) claims.get("roles");
    List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
    if (roles != null) {
      roleList = AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]));
    }
    return new JWTAuthenticationToken(userId, userName, roleList);
  }
}
