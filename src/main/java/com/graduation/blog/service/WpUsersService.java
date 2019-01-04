package com.graduation.blog.service;

import com.graduation.blog.domain.WpUsers;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
public interface WpUsersService {

  WpUsers selectByPrimaryKey(Long id);
}
