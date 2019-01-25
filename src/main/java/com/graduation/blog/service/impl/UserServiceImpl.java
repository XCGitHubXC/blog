package com.graduation.blog.service.impl;

import com.graduation.blog.constants.ValidateMessage;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.LoginInfoResponseDTO;
import com.graduation.blog.domain.dto.LoginRequestDTO;
import com.graduation.blog.domain.dto.RegisterRequestDTO;
import com.graduation.blog.enums.PlatformEnum;
import com.graduation.blog.service.UserService;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.CommonsUtils;
import com.graduation.blog.utils.Encrypt;
import com.graduation.blog.utils.ErrorCode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    User selectUser = userMapper.selectByLoginName(loginName);
    // 用户不存在
    Assert.isNotNull(selectUser, ErrorCode.USER_NOT_EXISTS, ValidateMessage.USER_NAME_NOT_EXISTS);
    // 密码md5加密
    password = Encrypt.md5(password);
    User user = userMapper.selectByLoginNameAndPwd(loginName, password);
    Assert.isNotNull(user, ErrorCode.PASSWORD_IS_WRONG, ValidateMessage.PASSWORD_IS_WRONG);
    // 组装dto
    LoginInfoResponseDTO loginInfoResponseDTO = BeanConvertUtils
        .copyBean(user, LoginInfoResponseDTO.class);
    return loginInfoResponseDTO;
  }

  @Override
  public List<User> listUser() {
    return userMapper.selectAll();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void userRegister(RegisterRequestDTO dto) {
    User user = BeanConvertUtils.copyBean(dto, User.class);
    String mobileNo = user.getMobileNo();
    String email = user.getEmail();
    User mobileUser = userMapper.selectByLoginName(mobileNo);
    Assert.isNull(mobileUser, ErrorCode.MOBILE_EXITS, ValidateMessage.MOBILE_EXITS);
    User emailUser = userMapper.selectByLoginName(email);
    Assert.isNull(emailUser, ErrorCode.EMAIL_EXITS, ValidateMessage.EMAIL_EXITS);
    String md5Password = Encrypt.md5(dto.getPassword());
    user.setPassword(md5Password);
    user.setId(CommonsUtils.get32BitUUID());
    // 普通用户
    user.setAuthority("0");
    int insert = userMapper.insert(user);
    if (1 != insert) {
      throw new AppException(ErrorCode.FAIL_DATABASE, "注册失败");
    }
  }
}
