package com.graduation.blog.service.impl;

import com.graduation.blog.dao.CommentMapper;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.Comment;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.requestdto.CommentBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ReplyCommRequestDTO;
import com.graduation.blog.domain.dto.responsedto.QueryCommResponseDTO;
import com.graduation.blog.service.CommentService;
import com.graduation.blog.utils.CommonsUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentMapper commentMapper;
  @Autowired
  private UserMapper userMapper;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void commentArticle(String curUserId, CommentBlogRequestDTO cbDTO) {
    Comment comment = new Comment();
    comment.setId(CommonsUtils.get32BitUUID());
    // 文章首次评论父id为"0"
    comment.setPId("0");
    comment.setArticleId(cbDTO.getArticleId());
    comment.setUserId(curUserId);
    comment.setContent(cbDTO.getContent());
    commentMapper.insert(comment);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void replyComm(String curUserId, ReplyCommRequestDTO rcDTO) {
    Comment comment = new Comment();
    comment.setId(CommonsUtils.get32BitUUID());
    comment.setPId(rcDTO.getCommId());
    comment.setArticleId(rcDTO.getArticleId());
    comment.setContent(rcDTO.getContent());
    comment.setUserId(curUserId);
    commentMapper.insert(comment);
  }

  @Override
  public List<QueryCommResponseDTO> selectComms(String articleId) {

    List<QueryCommResponseDTO> qcrDTOLists = new ArrayList<>();
    Example example = new Example(Comment.class);
    example.createCriteria().andEqualTo("articleId", articleId)
        .andEqualTo("pId", "0").andEqualTo("status", "0");
    List<Comment> comments = commentMapper.selectByExample(example);
    for (Comment c : comments) {
      QueryCommResponseDTO queryCommResponseDTO = new QueryCommResponseDTO();
      queryCommResponseDTO.setComment(c);
      User user = userMapper.selectByPrimaryKey(c.getUserId());
      queryCommResponseDTO.setUser(user);
      example = new Example(Comment.class);
      example.createCriteria().andEqualTo("pId", c.getId())
          .andEqualTo("status", "0");
      List<Comment> replyLists = commentMapper.selectByExample(example);
      queryCommResponseDTO.setReplyCount(Integer.valueOf(replyLists.size()));
      qcrDTOLists.add(queryCommResponseDTO);
    }

    return qcrDTOLists;
  }

  @Override
  public List<QueryCommResponseDTO> selectReply(String commId) {
    List<QueryCommResponseDTO> qcrDTOLists = new ArrayList<>();
    Example example = new Example(Comment.class);
    example.createCriteria().andEqualTo("pId", commId)
        .andEqualTo("status", "0");
    List<Comment> comments = commentMapper.selectByExample(example);
    for (Comment c : comments) {
      QueryCommResponseDTO queryCommResponseDTO = new QueryCommResponseDTO();
      queryCommResponseDTO.setComment(c);
      User user = userMapper.selectByPrimaryKey(c.getUserId());
      queryCommResponseDTO.setUser(user);
      example = new Example(Comment.class);
      example.createCriteria().andEqualTo("pId", c.getId())
          .andEqualTo("status", "0");
      List<Comment> replyLists = commentMapper.selectByExample(example);
      queryCommResponseDTO.setReplyCount(Integer.valueOf(replyLists.size()));
      qcrDTOLists.add(queryCommResponseDTO);
    }
    return qcrDTOLists;
  }


}
