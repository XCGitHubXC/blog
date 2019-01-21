package com.graduation.blog.dao;


import com.graduation.blog.domain.Focus;

public interface FocusMapper {

  int deleteByPrimaryKey(String id);

  int insert(Focus record);

  int insertSelective(Focus record);

  Focus selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Focus record);

  int updateByPrimaryKey(Focus record);
}