package com.graduation.blog.service;

import com.github.pagehelper.PageInfo;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.dto.requestdto.ArticleEditRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.domain.dto.requestdto.AuditBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.BlogsQueryRequestDTO;
import java.util.List;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
public interface ArticleService {


  /**
   * 博文搜索
   */
  List<Article> blogSearch(String keyword);

  /**
   *  发表博文
   */
  void publishBlog(String userId, ArticlePublishRequestDTO articlePublishRequestDTO);

  /**
   *  单个审核博文
   */
  void auditBlog(String userId, AuditBlogRequestDTO abRequestDTO);


  /**
   *  删除博文
   */
  void deleteBlog(String articleId);

  /**
   *  查询博文
   */
  Article selectBlog(String articleId);


  /**
   *  用户博文列表
   */
  PageInfo<Article> myBlogList(String userId, BlogsQueryRequestDTO blogsQueryRequestDTO);

  /**
   *  博文点赞
   */
  void fabulousBlog(String curUserId, String articleId);

  /**
   *  取消点赞
   */
  void cancleFabulous(String curUserId, String articleId);

  /**
   * 编辑博文
   */
  void editBlog(ArticleEditRequestDTO editRequestDTO);
}
