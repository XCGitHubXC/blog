package com.graduation.blog.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: xiachuan
 * @Date: 2019/1/28
 * @Description:
 */
@Data
@ApiModel("分页查询参数")
public class PageParam implements Serializable {

  private static final long serialVersionUID = -2434215078952977433L;

  /**
   * 开始行号
   */
  @ApiModelProperty(value = "开始行号")
  private String startNo = "0";

  /**
   * 每页记录数
   */
  @ApiModelProperty(value = "每页记录数")
  private String pageSize = "10";




}
