package com.graduation.blog.domain.dto.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/6
 * @Description:
 **/
@Data
@ApiModel(value = "登录成功dto")
public class LoginTokenResponseDTO implements Serializable {
  private static final long serialVersionUID = 940644291147800571L;

  /**
   * 登录用户信息
   */
  @ApiModelProperty(value = "登录用户信息")
  private LoginInfoResponseDTO loginInfo;

  /**
   * 认证令牌
   */
  @ApiModelProperty(value = "认证令牌")
  private String authToken;

  /**
   * 刷新令牌
   */
  @ApiModelProperty(value = "刷新令牌")
  private String refreshToken;
}
