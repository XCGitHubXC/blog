package com.graduation.blog.dao;


import com.graduation.blog.domain.WpUsers;
import org.springframework.stereotype.Repository;

@Repository
public interface WpUsersMapper {

  int deleteByPrimaryKey(Long id);

  int insert(WpUsers record);

  int insertSelective(WpUsers record);

  WpUsers selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(WpUsers record);

  int updateByPrimaryKey(WpUsers record);
}