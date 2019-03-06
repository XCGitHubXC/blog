package com.graduation.blog.service;

import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.responsedto.RecomBlogResponseDTO;
import java.util.List;

/**
 * @Author: xiachuan
 * @Date: 2019/1/21
 * @Description:
 */
public interface RecommendService {

  /**
   * 推荐用户
   */
  List<User> recomUsers();

  /**
   * 推荐博文
   */
  List<RecomBlogResponseDTO> recomBlogs();


  /**
   * 推荐类型博文
   */
  List<RecomBlogResponseDTO> recomTypeBlogs(String articleType);

}
