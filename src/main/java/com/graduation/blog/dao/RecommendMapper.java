package com.graduation.blog.dao;


import com.graduation.blog.domain.Recommend;

public interface RecommendMapper {

  int deleteByPrimaryKey(String id);

  int insert(Recommend record);

  int insertSelective(Recommend record);

  Recommend selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Recommend record);

  int updateByPrimaryKey(Recommend record);
}