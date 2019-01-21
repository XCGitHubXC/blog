package com.graduation.blog.domain.dto;

import com.graduation.blog.domain.User;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/6
 * @Description:
 **/

@Data
@ApiModel(value = "登录用户信息dto")
public class LoginInfoResponseDTO extends User implements Serializable {


  private static final long serialVersionUID = -144246312059458384L;


}
