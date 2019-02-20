package com.graduation.blog.dao;


import com.graduation.blog.base.MyMapper;
import com.graduation.blog.domain.Article;
import java.util.List;

public interface ArticleMapper extends MyMapper<Article> {

  /**
   * 根据ids查询
   */
  List<Article> selectByIds(List<String> ids);

}