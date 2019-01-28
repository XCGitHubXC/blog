package com.graduation.blog.service.impl;

import com.graduation.blog.dao.DictMapper;
import com.graduation.blog.domain.Dict;
import com.graduation.blog.service.DictService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@Service
@Slf4j
public class DictServiceImpl implements DictService {

  @Autowired
  private DictMapper dictMapper;


  @Override
  public List<Dict> listDict() {
    return dictMapper.selectAll();
  }
}
