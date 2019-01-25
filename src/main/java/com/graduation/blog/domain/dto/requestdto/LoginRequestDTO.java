package com.graduation.blog.domain.dto.requestdto;

import com.graduation.blog.constants.ValidateMessage;
import com.graduation.blog.enums.PlatformEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户密码登录dto
 *
 *
 */
@ApiModel("用户密码登录dto")
@Data
public class LoginRequestDTO implements Serializable {


  private static final long serialVersionUID = -1286941728372142547L;
  /**
   * 平台类型(WEB[网页],WECHAT[小程序],MANAGE[后台生成],OTHER[其他])
   */
  @ApiModelProperty(value = "平台类型(WEB[网页],WECHAT[小程序],MANAGE[后台生成],OTHER[其他])", required = true)
  @NotNull(message = ValidateMessage.PLATFORM_NOT_NULL)
  private PlatformEnum platform;
  /**
   * 手机号或用户名
   */
  @ApiModelProperty(value = "手机号或用户名", required = true)
  @NotBlank(message = ValidateMessage.LOGIN_NAME_NOT_BLANK)
  private String loginName;

  /**
   * 登录密码
   */
  @ApiModelProperty(value = "登录密码", required = true)
  @NotBlank(message = ValidateMessage.PASSWORD_NOT_BLANK)
  private String password;

}
