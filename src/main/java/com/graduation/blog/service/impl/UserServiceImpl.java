package com.graduation.blog.service.impl;

import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.LoginRequestDTO;
import com.graduation.blog.enums.PlatformEnum;
import com.graduation.blog.service.UserService;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.Encrypt;
import com.graduation.blog.utils.ErrorCode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;


  @Override
  public User selectUserById(String id) {
    return userMapper.selectByPrimaryKey(id);
  }

  @Override
  public LoginInfoResponseDTO login(LoginRequestDTO dto) {
    // 获取前台参数
    String password = dto.getPassword();
    String loginName = dto.getLoginName();
    PlatformEnum platform = dto.getPlatform();
    // 密码md5加密
    password = Encrypt.md5(password);
    User user = userMapper.selectByLoginNameAndPwd(loginName, password);
    Assert.isNotNull(user, ErrorCode.USER_NOT_EXISTS, "用户不存在");
    // 组装dto
    LoginInfoResponseDTO loginInfoResponseDTO = BeanConvertUtils
        .copyBean(user, LoginInfoResponseDTO.class);
    return loginInfoResponseDTO;
  }

  @Override
  public List<User> listUser() {
    return userMapper.selectAll();
  }
}
