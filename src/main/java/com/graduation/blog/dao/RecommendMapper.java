package com.graduation.blog.dao;


import com.graduation.blog.base.MyMapper;
import com.graduation.blog.domain.Recommend;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecommendMapper extends MyMapper<Recommend> {

  List<String>  recomBlogs();

  List<String>  recomTypeBlogs(@Param("articleType") String articleType);

}