package com.graduation.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.blog.dao.ArticleMapper;
import com.graduation.blog.dao.StoreMapper;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.Store;
import com.graduation.blog.domain.dto.PageParam;
import com.graduation.blog.service.StoreService;
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
public class StoreServiceImpl implements StoreService {

  @Autowired
  private StoreMapper storeMapper;
  @Autowired
  ArticleMapper articleMapper;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void storeBlog(String curUserId, String articleId) {
    Store store = new Store();
    store.setId(CommonsUtils.get32BitUUID());
    store.setUserId(curUserId);
    store.setArticleId(articleId);
    storeMapper.insert(store);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void cancelStoreBlog(String curUserId, String articleId) {
    Example example = new Example(Store.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("articleId", articleId)
        .andEqualTo("status", "0");
    List<Store> stores = storeMapper.selectByExample(example);
    if (null != stores) {
      for (Store s : stores) {
        storeMapper.delete(s);
      }
    }
  }

  @Override
  public PageInfo<Article> myStores(String curUserId, PageParam pageParam) {
    List<String> result = new ArrayList<>();
    Example example = new Example(Store.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("status", "0");
    List<Store> stores = storeMapper.selectByExample(example);

    if (stores == null) {
      return null;
    }
    for (Store s : stores) {
      result.add(s.getArticleId());
    }
    PageInfo<Article> articlePageInfo = PageHelper.startPage(Integer.valueOf(pageParam.getPageNum()),
        Integer.valueOf(pageParam.getPageSize()), true)
        .doSelectPageInfo(() -> articleMapper.selectByIds(result));
    return articlePageInfo;
  }
}
