package com.graduation.blog.domain.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: xiachuan
 * @Date: 2019/1/28
 * @Description:
 */
@Data
@ApiModel("我的博文列表返回对象")
public class BlogListResponseDTO implements Serializable {

  /**
   * 标题
   */
  @ApiModelProperty(value = "标题")
  private String title;

  /**
   * 文章类型
   */
  @ApiModelProperty(value = "文章类型")
  private String articleType;


  /**
   * 阅读数
   */
  @ApiModelProperty(value = "阅读数")
  private String readNum;

  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间", hidden = true)
  private Date createTime;



  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }
}
