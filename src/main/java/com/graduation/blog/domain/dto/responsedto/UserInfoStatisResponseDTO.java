package com.graduation.blog.domain.dto.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: xiachuan
 * @Date: 2019/2/23
 * @Description:
 */
@Data
@ApiModel(value = "刷新成功令牌")
public class UserInfoStatisResponseDTO implements Serializable {

  private static final long serialVersionUID = 655012419834558463L;


  /**
   * 头像id
   */
  @ApiModelProperty(value = "头像id")
  private String fileId;

  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称")
  private String nickName;

  /**
   * 关注状态[0关注,1已关注]
   */
  @ApiModelProperty(value = "关注状态[0关注,1已关注]")
  private String focusFlag;

  /**
   * 原创数量
   */
  @ApiModelProperty(value = "原创数量")
  private String originalNum;

  /**
   * 粉丝数量
   */
  @ApiModelProperty(value = "粉丝数量")
  private String fanNum;

  /**
   * 喜欢数量
   */
  @ApiModelProperty(value = "喜欢数量")
  private String likeNum;

  /**
   * 评论数量
   */
  @ApiModelProperty(value = "评论数量")
  private String commentNum;

  /**
   * 积分
   */
  @ApiModelProperty(value = "积分")
  private String score;

  /**
   * 访问量
   */
  @ApiModelProperty(value = "访问量")
  private String viewNum;



}
