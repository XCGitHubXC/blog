package com.graduation.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实体基类
 */
@Data
@ApiModel(value = "实体基类")
public class BaseObject implements Serializable {

  /**
   * 自增
   */
  @Id
  @Column(name = "id")
  @ApiModelProperty(value = "id")
  private String id;

  /**
   * 创建人
   */
  @Column(name = "create_user")
  @ApiModelProperty(value = "创建人", hidden = true)
  private String createUser;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  @ApiModelProperty(value = "创建时间", hidden = true)
  private Date createTime;

  /**
   * 修改人
   */
  @Column(name = "update_user")
  @ApiModelProperty(value = "修改人", hidden = true)
  private String updateUser;

  /**
   * 修改时间
   */
  @Column(name = "update_time")
  @ApiModelProperty(value = "修改时间", hidden = true)
  private Date updateTime;

  /**
   * 版本号
   */
  @ApiModelProperty(value = "版本号", hidden = true)
  private Integer version;

  /**
   * 状态 0 正常 1 删除
   */
  @ApiModelProperty(value = "状态 0 正常 1 删除", hidden = true)
  private String status;

  /**
   * 备注
   */
  @ApiModelProperty(value = "备注", hidden = false)
  private String remark;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date getUpdateTime() {
    return updateTime;
  }
}
