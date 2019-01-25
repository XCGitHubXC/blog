package com.graduation.blog.domain.dto.requestdto;

import com.graduation.blog.constants.ValidateMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户注册dto
 *
 */
@ApiModel("用户注册dto")
@Data
public class RegisterRequestDTO implements Serializable {


  private static final long serialVersionUID = 6475843158064201547L;

  /**
   * 手机号
   */
  @ApiModelProperty(value = "手机号", required = true)
  @NotBlank(message = ValidateMessage.MOBILE_PHONE_NOT_BLANK)
  private String mobileNo;

  /**
   * 邮箱
   */
  @ApiModelProperty(value = "邮箱", required = true)
  @NotBlank(message = ValidateMessage.EMAIL_NOT_BLANK)
  private String email;

  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称", required = true)
  @NotBlank(message = ValidateMessage.USER_NAME_NOT_BLANK)
  private String name;

  /**
   * 简介
   */
  @ApiModelProperty(value = "简介")
  private String summary;


  /**
   * 签名
   */
  @ApiModelProperty(value = "签名")
  private String signature;


  /**
   * 职业
   */
  @ApiModelProperty(value = "职业")
  private String profession;


  /**
   * 地区
   */
  @ApiModelProperty(value = "地区")
  private String region;

  /**
   * 登录密码
   */
  @ApiModelProperty(value = "登录密码", required = true)
  @NotBlank(message = ValidateMessage.PASSWORD_NOT_BLANK)
  private String password;


  /**
   * 性别
   */
  @ApiModelProperty(value = "[(0 女生),(1 男生)]")
  private String gender;

  /**
   * 生日
   */
  @ApiModelProperty(value = "生日")
  private Date birthday;

}
