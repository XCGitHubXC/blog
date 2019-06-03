package com.graduation.blog.service.impl;

import com.graduation.blog.dao.ArticleMapper;
import com.graduation.blog.dao.CommentMapper;
import com.graduation.blog.dao.RecommendMapper;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.Comment;
import com.graduation.blog.domain.Recommend;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.responsedto.RecomBlogResponseDTO;
import com.graduation.blog.service.RecommendService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

  @Autowired
  private RecommendMapper recommendMapper;
  @Autowired
  private ArticleMapper articleMapper;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private CommentMapper commentMapper;


  @Override
  public List<User> recomUsers() {

    Example example = new Example(Recommend.class);
    example.createCriteria().andEqualTo("type", "1")
        .andEqualTo("status", "0");
    List<Recommend> recommends = recommendMapper.selectByExample(example);


    return null;
  }

  @Override
  public List<RecomBlogResponseDTO> recomBlogs() {
    List<String> recomBlogIds = recommendMapper.recomBlogs();
    List<RecomBlogResponseDTO> recomBlogResponseDTOS = new ArrayList<>();
    Set<String> recomBlogSet = new HashSet<String>();
    recomBlogSet.addAll(recomBlogIds);
    for (String blogId : recomBlogSet) {
      RecomBlogResponseDTO recomBlogResponseDTO = new RecomBlogResponseDTO();
      Article article = articleMapper.selectByPrimaryKey(blogId);
      User user = userMapper.selectByPrimaryKey(article.getUserId());
      BeanUtils.copyProperties(article, recomBlogResponseDTO);
      // BeanUtils.copyProperties(user, recomBlogResponseDTO);
      Example example = new Example(Comment.class);
      example.createCriteria().andEqualTo("articleId", blogId)
          .andEqualTo("status", "0");
      int commentCount = commentMapper.selectCountByExample(example);
      recomBlogResponseDTO.setCommentNum(commentCount + "");
      recomBlogResponseDTOS.add(recomBlogResponseDTO);
    }
    return recomBlogResponseDTOS;
  }

  @Override
  public List<RecomBlogResponseDTO> recomTypeBlogs(String articleType) {
    List<String> recomBlogIds = recommendMapper.recomTypeBlogs(articleType);
    Set<String> recomBlogSet = new HashSet<String>();
    recomBlogSet.addAll(recomBlogIds);

    List<RecomBlogResponseDTO> recomBlogResponseDTOS = new ArrayList<>();
    for (String blogId : recomBlogSet) {
      RecomBlogResponseDTO recomBlogResponseDTO = new RecomBlogResponseDTO();
      Article article = articleMapper.selectByPrimaryKey(blogId);
      User user = userMapper.selectByPrimaryKey(article.getUserId());
      BeanUtils.copyProperties(article, recomBlogResponseDTO);
      // BeanUtils.copyProperties(user, recomBlogResponseDTO);
      Example example = new Example(Comment.class);
      example.createCriteria().andEqualTo("articleId", blogId)
          .andEqualTo("status", "0");
      int commentCount = commentMapper.selectCountByExample(example);
      recomBlogResponseDTO.setCommentNum(commentCount + "");
      recomBlogResponseDTOS.add(recomBlogResponseDTO);
    }
    return recomBlogResponseDTOS;
  }
}
