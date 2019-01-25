package com.graduation.blog.domain.dto.requestdto;

import com.graduation.blog.constants.ValidateMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * token刷新 dto
 * 
 *
 */
@ApiModel("token刷新dto")
@Data
public class RefreshTokenRequestDTO implements Serializable {
  private static final long serialVersionUID = -1864065199684162305L;
  /**
   * token刷新码
   */
  @ApiModelProperty(value = "token刷新码", required = true)
  @NotBlank(message = ValidateMessage.REFRESH_TOKEN_NOT_BLANK)
  private String refreshToken;
}
