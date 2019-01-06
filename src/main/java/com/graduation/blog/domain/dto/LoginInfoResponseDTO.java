package com.graduation.blog.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/6
 * @Description:
 **/

@Data
@ApiModel(value = "登录用户信息dto")
public class LoginInfoResponseDTO {
  private static final long serialVersionUID = 1939993907517003148L;
  @ApiModelProperty(value = "用户id")
  private String userId;
  @ApiModelProperty(value = "登录名")
  private String userName;
  @ApiModelProperty(value = "用户类型")
  private String userType;
  @ApiModelProperty(value = "是否实名认证")
  private Boolean isRealNameVerify;
  @ApiModelProperty(value = "角色")
  private List<String> roles;

  public LoginInfoResponseDTO() {

  }


}
