package com.graduation.blog.service.impl;

import com.graduation.blog.dao.WpUsersMapper;
import com.graduation.blog.domain.WpUsers;
import com.graduation.blog.service.WpUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@Service
public class WpUserServiceImpl implements WpUsersService {

  @Autowired
  private WpUsersMapper wpUsersMapper;


  @Override
  public WpUsers selectByPrimaryKey(Long id) {
    return wpUsersMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<WpUsers> listWpUsers() {
    return null;
  }
}
