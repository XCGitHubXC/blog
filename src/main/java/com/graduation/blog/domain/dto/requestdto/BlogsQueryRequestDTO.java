package com.graduation.blog.domain.dto.requestdto;

import com.graduation.blog.domain.dto.PageParam;
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
@ApiModel("博文查询请求参数")
public class BlogsQueryRequestDTO extends PageParam implements Serializable {

  private static final long serialVersionUID = 5998430923801033909L;

  /**
   * userId
   */
  @ApiModelProperty(value = "userId")
  private String userId;

}
