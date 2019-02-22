package com.graduation.blog.service.impl;

import com.google.common.base.Strings;
import com.graduation.blog.constants.ValidateMessage;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.requestdto.LoginRequestDTO;
import com.graduation.blog.domain.dto.requestdto.RegisterRequestDTO;
import com.graduation.blog.domain.dto.requestdto.UserMsgUpdateRequestDTO;
import com.graduation.blog.domain.dto.requestdto.UserPwdUpdateRequestDTO;
import com.graduation.blog.domain.dto.responsedto.LoginInfoResponseDTO;
import com.graduation.blog.enums.PlatformEnum;
import com.graduation.blog.service.UserService;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.CommonsUtils;
import com.graduation.blog.utils.Encrypt;
import com.graduation.blog.utils.ErrorCode;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;


  @Override
  public User selectUserById(String id) {
    return userMapper.selectByPrimaryKey(id);
  }

  @Override
  public LoginInfoResponseDTO login(LoginRequestDTO dto) {
    log.debug("登录请求参数:{}", dto);
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
    log.debug("登录成功");
    // 组装dto
    return BeanConvertUtils
        .copyBean(user, LoginInfoResponseDTO.class);
  }

  @Override
  public List<User> listUser() {
    return userMapper.selectAll();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void userRegister(RegisterRequestDTO dto) {
    log.debug("注册请求参数:{}", dto);
    User user = BeanConvertUtils.copyBean(dto, User.class);
    String mobileNo = user.getMobileNo();
    String email = user.getEmail();
    User mobileUser = userMapper.selectByLoginName(mobileNo);
    // 手机号已经注册
    Assert.isNull(mobileUser, ErrorCode.MOBILE_EXITS, ValidateMessage.MOBILE_EXITS);
    User emailUser = userMapper.selectByLoginName(email);
    // 邮箱已经注册
    Assert.isNull(emailUser, ErrorCode.EMAIL_EXITS, ValidateMessage.EMAIL_EXITS);
    String md5Password = Encrypt.md5(dto.getPassword());
    user.setPassword(md5Password);
    user.setId(CommonsUtils.get32BitUUID());
    String gender = user.getGender();
    // 女生头像
    if (Strings.isNullOrEmpty(gender) && "0".equals(gender)) {
      user.setFileId("d4e6da7087854d3cae5664ee663ca635");
    } else {
      user.setFileId("648a3e9dd70244ffb1626d653e6c820d");
    }
    // 普通用户
    user.setAuthority("0");
    user.setScore(1);
    int insert = userMapper.insert(user);
    if (1 != insert) {
      throw new AppException(ErrorCode.FAIL_DATABASE, "注册失败");
    }
    log.debug("注册成功");
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void userPwdUpdate(UserPwdUpdateRequestDTO dto, String userId) {
    log.debug("密码修改开始");
    User user = new User();
    user.setId(userId);
    List<User> select = userMapper.select(user);
    user = select.get(0);
    String password = user.getPassword();
    String oldPassword = Encrypt.md5(dto.getOldPassword());
    if (!password.equals(oldPassword)) {
      throw new AppException(ErrorCode.PASSWORD_IS_WRONG, "原密码错误");
    }
    user.setPassword(Encrypt.md5(dto.getNewPassowrd()));
    userMapper.updateByPrimaryKeySelective(user);
    log.debug("密码修改成功");
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void userMsgUpdate(UserMsgUpdateRequestDTO dto, String userId) {
    User user = new User();
    user.setId(userId);
    List<User> select = userMapper.select(user);
    user = select.get(0);
    BeanUtils.copyProperties(dto, user);
    int i = userMapper.updateByPrimaryKeySelective(user);
    log.info("跟新数量：{}", i);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void userHeadUpdate(String fileId, String userId) {
    User user = new User();
    user.setId(userId);
    List<User> select = userMapper.select(user);
    user = select.get(0);
    user.setFileId(fileId);
    userMapper.updateByPrimaryKeySelective(user);
  }
}
