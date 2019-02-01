package com.graduation.blog.service.impl;

import com.graduation.blog.dao.FocusMapper;
import com.graduation.blog.domain.Focus;
import com.graduation.blog.service.FocusService;
import com.graduation.blog.utils.CommonsUtils;
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

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void focusUser(String curUserId, String focusUserId) {
    Focus focus = new Focus();
    focus.setId(CommonsUtils.get32BitUUID());
    focus.setUserId(curUserId);
    focus.setFocusUserId(focusUserId);
    focusMapper.insert(focus);
  }

  @Override
  public List<String> myFocus(String curUserId) {
    List<String> result = new ArrayList<>();
    Example example = new Example(Focus.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("status", "0");
    List<Focus> foci = focusMapper.selectByExample(example);
    if (foci == null) {
      return null;
    }
    for (Focus f : foci) {
      result.add(f.getFocusUserId());
    }
    return result;
  }
}
