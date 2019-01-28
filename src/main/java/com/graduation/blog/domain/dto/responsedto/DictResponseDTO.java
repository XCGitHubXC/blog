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
@ApiModel(value = "字典表返回信息DTO")
public class DictResponseDTO implements Serializable {


  private static final long serialVersionUID = -5399887437889649436L;

  /**
   * 名称
   */
  @ApiModelProperty(value = "名称")
  private String name;

  /**
   * code
   */
  @ApiModelProperty(value = "code")
  private String code;

  /**
   * 类别
   */
  @ApiModelProperty(value = "类别")
  private String describes;

}
