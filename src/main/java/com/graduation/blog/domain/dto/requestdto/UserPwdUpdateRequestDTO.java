package com.graduation.blog.domain.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户密码修改dto
 *
 */
@ApiModel("用户密码修改dto")
@Data
public class UserPwdUpdateRequestDTO implements Serializable {


  private static final long serialVersionUID = 2454539741950602322L;
  /**
   * 原密码
   */
  @ApiModelProperty(value = "原密码", required = true)
  @NotBlank(message = "原密码不能为空")
  private String oldPassword;

  /**
   * 新密码
   */
  @ApiModelProperty(value = "新密码")
  @NotBlank(message = "新密码不能为空")
  private String newPassowrd;


}
