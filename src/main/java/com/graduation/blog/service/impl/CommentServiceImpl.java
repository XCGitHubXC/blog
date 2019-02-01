package com.graduation.blog.service.impl;

import com.graduation.blog.dao.CommentMapper;
import com.graduation.blog.domain.Comment;
import com.graduation.blog.domain.dto.requestdto.CommentBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ReplyCommRequestDTO;
import com.graduation.blog.service.CommentService;
import com.graduation.blog.utils.CommonsUtils;
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
  public List<Comment> selectComms(String articleId) {
    Example example = new Example(Comment.class);
    example.createCriteria().andEqualTo("articleId", articleId)
        .andEqualTo("pId", "0").andEqualTo("status", "0");
    List<Comment> comments = commentMapper.selectByExample(example);
    return comments;
  }

  @Override
  public List<Comment> selectReply(String commId) {
    Example example = new Example(Comment.class);
    example.createCriteria().andEqualTo("pId", commId)
        .andEqualTo("status", "0");
    List<Comment> comments = commentMapper.selectByExample(example);
    return comments;
  }


}
