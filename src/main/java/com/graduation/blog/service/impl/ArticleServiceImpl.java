package com.graduation.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.blog.dao.ArticleMapper;
import com.graduation.blog.dao.CommentMapper;
import com.graduation.blog.dao.FabulousMapper;
import com.graduation.blog.dao.RecommendMapper;
import com.graduation.blog.dao.StoreMapper;
import com.graduation.blog.dao.UserMapper;
import com.graduation.blog.domain.Article;
import com.graduation.blog.domain.Fabulous;
import com.graduation.blog.domain.Recommend;
import com.graduation.blog.domain.Store;
import com.graduation.blog.domain.User;
import com.graduation.blog.domain.dto.requestdto.ArticleEditRequestDTO;
import com.graduation.blog.domain.dto.requestdto.ArticlePublishRequestDTO;
import com.graduation.blog.domain.dto.requestdto.AuditBlogRequestDTO;
import com.graduation.blog.domain.dto.requestdto.BlogsQueryRequestDTO;
import com.graduation.blog.domain.dto.responsedto.SelectBlogResponseDTO;
import com.graduation.blog.enums.AuditClassEnum;
import com.graduation.blog.enums.UserTypeEnum;
import com.graduation.blog.imageutils.Fingerprint;
import com.graduation.blog.imageutils.ImageGenerate;
import com.graduation.blog.service.ArticleService;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.CommonsUtils;
import com.graduation.blog.utils.ErrorCode;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleMapper articleMapper;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private FabulousMapper fabulousMapper;
  @Autowired
  private RecommendMapper recommendMapper;
  @Autowired
  private CommentMapper commentMapper;
  @Autowired
  private StoreMapper storeMapper;


  @Override
  public List<Article> blogSearch(String keyword) {
    Example example = new Example(Article.class);
    example.createCriteria().andLike("title", keyword)
        .andEqualTo("status", "0");
    List<Article> articles = articleMapper.selectByExample(example);
    return articles;
  }

  @Override
  public String picSearch(String fileId) {
    List<Article> articles = articleMapper.selectAll();
    String s1 = null;
    String s2 = null;
    try {
      s1 = Fingerprint.getFingerprintPhash("C:\\data\\fileStorage\\blog\\"
          + fileId + ".jpg");
    } catch (Exception e) {
      s1 = Fingerprint.getFingerprintPhash("C:\\data\\fileStorage\\blog\\"
          + fileId + ".png");
    }

    for (Article article : articles) {
      try {
        s2 = Fingerprint.getFingerprintPhash("C:\\data\\fileStorage\\blog\\"
            + article.getId() + ".jpg");
      } catch (Exception e) {
        continue;
      }
      if (Fingerprint.hammingDistance(s1, s2) <= 5) {
        return article.getId();
      }
    }
    return null;
  }

  @Override
  public void publishBlog(String userId, ArticlePublishRequestDTO articlePublishRequestDTO) {
    Article article = BeanConvertUtils.copyBean(articlePublishRequestDTO, Article.class);
    article.setId(CommonsUtils.get32BitUUID());
    article.setUserId(userId);
    article.setReadNum("0");
    article.setFabulous("0");
    // 需要审核
    // article.setAudit(AuditClassEnum.PASS.getCode());
    // 不需要审核   master测试
    article.setAudit(AuditClassEnum.PASS.getCode());
    articleMapper.insert(article);

    // 处理文章中图片,生成图片存储在服务器中
    try {
      String content = article.getContent();
      String s1 = StringUtils.substringAfter(content, "src=\"");
      String s2 = StringUtils.substringBefore(s1, "\">");
      ImageGenerate.generPic(s2, "C:/data/fileStorage/blog", article.getId() + ".jpg");

    } catch (Exception e) {

    }

  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void auditBlog(String userId, AuditBlogRequestDTO auditBlogRequestDTO) {
    User user = userMapper.selectByPrimaryKey(userId);
    if (UserTypeEnum.ORDINARY.getCode().equals(user.getAuthority())) {
      throw new AppException(ErrorCode.ACCESS_DENIED, "权限不足");
    }
    Article article = articleMapper.selectByPrimaryKey(auditBlogRequestDTO.getArticleId());
    Assert.isNotNull(article, ErrorCode.RESULT_EMPTY, "该文章不存在");
    article.setAudit(auditBlogRequestDTO.getAuditStr());
    articleMapper.updateByPrimaryKeySelective(article);
    // 博文审核通过，用户积分加1
    if (auditBlogRequestDTO.getAuditStr().equals("1")) {
      User blogUser = userMapper.selectByPrimaryKey(article.getUserId());
      blogUser.setScore(blogUser.getScore() + 1);
      userMapper.updateByPrimaryKeySelective(blogUser);
    }

  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteBlog(String articleId) {
    articleMapper.deleteByPrimaryKey(articleId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public SelectBlogResponseDTO selectBlog(String articleId, String curUserId) {
    Article article = articleMapper.selectByPrimaryKey(articleId);
    Example example = new Example(Fabulous.class);
    example.createCriteria().andEqualTo("articleId", articleId)
        .andEqualTo("status", "0");
    // 查询博文点赞数量
    List<Fabulous> fabulous = fabulousMapper.selectByExample(example);
    article.setFabulous(fabulous.size() + "");
    // 博文阅读数加一
    String newReadNum = (Integer.valueOf(article.getReadNum()) + 1) + "";
    // 阅读数或者点赞数大于50，进入推荐表
    if (Integer.valueOf(newReadNum) > 50
        || Integer.valueOf(article.getFabulous()) > 50) {
      Recommend recommend = new Recommend();
      recommend.setId(CommonsUtils.get32BitUUID());
      recommend.setArticleId(article.getId());
      recommend.setStatus("0");
      // 推荐的是博文
      recommend.setType("0");
      recommend.setArticleType(article.getType());
      recommendMapper.insert(recommend);
    }
    article.setReadNum(newReadNum);
    articleMapper.updateByPrimaryKeySelective(article);

    SelectBlogResponseDTO sBlog = new SelectBlogResponseDTO();
    BeanUtils.copyProperties(article, sBlog);

    int commentCount = commentMapper.selectCountByExample(example);
    sBlog.setCommentNum(commentCount + "");

    if ("null".equals(curUserId)) {
      sBlog.setFabulousFlag("0");
      sBlog.setStoreFlag("0");
    } else {
      Example example1 = new Example(Fabulous.class);
      example1.createCriteria().andEqualTo("userId", curUserId)
          .andEqualTo("articleId", articleId).andEqualTo("status", "0");
      List<Fabulous> fabulous1 = fabulousMapper.selectByExample(example1);
      if (0 != fabulous1.size()) {
        // throw new AppException(ErrorCode.RESULT_EMPTY, "已经点赞过该文章");
        sBlog.setFabulousFlag("1");
      } else {
        sBlog.setFabulousFlag("0");
      }

      Example example2 = new Example(Store.class);
      example2.createCriteria().andEqualTo("userId", curUserId)
          .andEqualTo("articleId", articleId).andEqualTo("status", "0");
      List<Store> stores = storeMapper.selectByExample(example2);
      if (0 != stores.size()) {
        sBlog.setStoreFlag("1");
      } else {
        sBlog.setStoreFlag("0");
      }
    }



    return sBlog;
  }

  @Override
  public PageInfo<Article> myBlogList(String userId, BlogsQueryRequestDTO bqrDTO) {
    Example example = new Example(Article.class);
    example.createCriteria().andEqualTo("userId", userId)
      .andEqualTo("status", "0").andEqualTo("audit", "1");
    PageInfo<Article> articlePageInfo = PageHelper.startPage(Integer.valueOf(bqrDTO.getPageNum()),
        Integer.valueOf(bqrDTO.getPageSize()), true)
        .doSelectPageInfo(() -> articleMapper.selectByExample(example));
    return articlePageInfo;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void fabulousBlog(String curUserId, String articleId) {
    Example example = new Example(Fabulous.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("articleId", articleId).andEqualTo("status", "0");
    List<Fabulous> fabulous = fabulousMapper.selectByExample(example);
    if (0 != fabulous.size()) {
      throw new AppException(ErrorCode.RESULT_EMPTY, "已经点赞过该文章");
    }
    Fabulous fabulous1 = new Fabulous();
    fabulous1.setId(CommonsUtils.get32BitUUID());
    fabulous1.setArticleId(articleId);
    fabulous1.setUserId(curUserId);
    fabulousMapper.insert(fabulous1);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void cancleFabulous(String curUserId, String articleId) {
    Example example = new Example(Fabulous.class);
    example.createCriteria().andEqualTo("userId", curUserId)
        .andEqualTo("articleId", articleId).andEqualTo("status", "0");
    List<Fabulous> fabulous = fabulousMapper.selectByExample(example);
    if (0 != fabulous.size()) {
      fabulousMapper.delete(fabulous.get(0));
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void editBlog(ArticleEditRequestDTO editRequestDTO) {
    String articleId = editRequestDTO.getArticleId();
    Article article = articleMapper.selectByPrimaryKey(articleId);
    BeanUtils.copyProperties(editRequestDTO, article);
    articleMapper.updateByPrimaryKeySelective(article);
  }
}
