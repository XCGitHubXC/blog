package com.graduation.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@ApiModel("字典表")
public class Dict implements Serializable {


  private static final long serialVersionUID = 7951736642731005059L;
  /**
   * id
   */
  @ApiModelProperty(value = "id")
  private Long id;

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
   * 描述
   */
  @ApiModelProperty(value = "id")
  private String describes;

  /**
   * 类别
   */
  @ApiModelProperty(value = "类别")
  private String type;

  private Integer showOrder;

  private String status;

  private String createUser;

  private Date createTime;

  private String updateUser;

  private Date updateTime;

  private String version;

  private String remark;

}