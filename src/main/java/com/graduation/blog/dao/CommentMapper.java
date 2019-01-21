package com.graduation.blog.dao;


import com.graduation.blog.domain.Comment;

public interface CommentMapper {

  int deleteByPrimaryKey(String id);

  int insert(Comment record);

  int insertSelective(Comment record);

  Comment selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Comment record);

  int updateByPrimaryKey(Comment record);
}