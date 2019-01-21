package com.graduation.blog.dao;


import com.graduation.blog.domain.Store;

public interface StoreMapper {

  int deleteByPrimaryKey(String id);

  int insert(Store record);

  int insertSelective(Store record);

  Store selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Store record);

  int updateByPrimaryKey(Store record);
}