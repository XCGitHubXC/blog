package com.graduation.blog.service;

import com.graduation.blog.domain.dto.requestdto.CommentBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ReplyCommRequestDTO;
import com.graduation.blog.domain.dto.responsedto.QueryCommResponseDTO;
import java.util.List;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
public interface CommentService {

  /**
   *  评论文章
   */
  void commentArticle(String curUserId, CommentBlogRequestDTO cbDTO);

  /**
   *  回复评论
   */
  void replyComm(String curUserId, ReplyCommRequestDTO rcDTO);



  /**
   *  查询文章的评论
   */
  List<QueryCommResponseDTO> selectComms(String articleId);


  /**
   *  查询回复
   */
  List<QueryCommResponseDTO> selectReply(String commId);

}
