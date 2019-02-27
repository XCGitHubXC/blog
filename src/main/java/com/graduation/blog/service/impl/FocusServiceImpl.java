package com.graduation.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.blog.dao.FocusMapper;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.Focus;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.PageParam;
import com.graduation.blog.service.FocusService;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.CommonsUtils;
import com.graduation.blog.utils.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
@Service
@Slf4j
public class FocusServiceImpl implements FocusService {

  @Autowired
  private FocusMapper focusMapper;
  @Autowired
  private UserMapper userMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void focusUser(String curUserId, String focusUserId) {
    User user = userMapper.selectByPrimaryKey(focusUserId);
    if (user == null) {
      throw new AppException(ErrorCode.RESULT_EMPTY, "关注用户不存在");
    }
    Example example = new Example(Focus.class);
    example.createCriteria().andEqualTo("focusUserId", focusUserId)
        .andEqualTo("userId", curUserId).andEqualTo("status", "0");
    List<Focus> foci = focusMapper.selectByExample(example);
    if (0 != foci.size()) {
      throw new AppException(ErrorCode.RESULT_EMPTY, "该用户已经被关注");
    }
    // 被关注这积分加1
    user.setScore(user.getScore() + 1);
    userMapper.updateByPrimaryKeySelective(user);
    Focus focus = new Focus();
    focus.setId(CommonsUtils.get32BitUUID());
    focus.setUserId(curUserId);
    focus.setFocusUserId(focusUserId);
    focusMapper.insert(focus);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void cancelFocusUser(String curUserId, String focusUserId) {
    Example example = new Example(Focus.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("focusUserId", focusUserId)
        .andEqualTo("status", "0");
    List<Focus> foci = focusMapper.selectByExample(example);
    if (0 != foci.size()) {
      for (Focus f : foci) {
        focusMapper.delete(f);
      }
    }
  }

  /**
   * @Author :xiachuan
   * @Date :2019/2/1
   * @Description : 我的关注
   * @Param :[curUserId]
   * @return :java.util.List<java.lang.String>
   */
  @Override
  public PageInfo<User> myFocus(String curUserId, PageParam pageParam) {
    List<String> result = new ArrayList<>();
    Example example = new Example(Focus.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("status", "0");
    List<Focus> foci = focusMapper.selectByExample(example);
    if (foci.size() == 0) {
      return null;
    }
    for (Focus f : foci) {
      result.add(f.getFocusUserId());
    }
    PageInfo<User> userPageInfo = PageHelper.startPage(Integer.valueOf(pageParam.getPageNum()),
        Integer.valueOf(pageParam.getPageSize()), true)
        .doSelectPageInfo(() -> userMapper.selectByIds(result));
    return userPageInfo;
  }

  /**
   * @Author :xiachuan
   * @Date :2019/2/1
   * @Description : 我的粉丝
   * @Param :[curUserId]
   * @return :java.util.List<java.lang.String>
   */
  @Override
  public PageInfo<User> myFans(String curUserId, PageParam pageParam) {
    List<String> result = new ArrayList<>();
    Example example = new Example(Focus.class);
    example.createCriteria().andEqualTo("focusUserId", curUserId)
        .andEqualTo("status", "0");
    List<Focus> foci = focusMapper.selectByExample(example);
    if (0 == foci.size()) {
      return null;
    }
    for (Focus f : foci) {
      result.add(f.getUserId());
    }
    PageInfo<User> userPageInfo = PageHelper.startPage(Integer.valueOf(pageParam.getPageNum()),
        Integer.valueOf(pageParam.getPageSize()), true)
        .doSelectPageInfo(() -> userMapper.selectByIds(result));
    List<User> userList = userPageInfo.getList();
    for (User u : userList) {
      Example example1 = new Example(Focus.class);
      example1.createCriteria().andEqualTo("focusUserId", u.getId())
          .andEqualTo("userId", curUserId).andEqualTo("status", "0");
      List<Focus> foci1 = focusMapper.selectByExample(example1);
      if (0 == foci1.size()) {
        // 我没有关注我的粉丝
        u.setRemark("0");
      } else {
        // 我关注了我的粉丝
        u.setRemark("1");
      }
    }
    userPageInfo.setList(userList);
    return userPageInfo;
  }
}
