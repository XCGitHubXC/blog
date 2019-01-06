package com.graduation.blog.utils;

/**
 * Created by zli on 2018/5/3.
 *
 * 常量
 */
public class BlogConst {

  /**
   * 问询笔录存储的文件ID
   */
  public static final String ASK_FILE_ID = "askFileId";

  /**
   * 问询笔录存储的文件URl
   */
  public static final String ASK_FILE_URL = "askFileUrl";

  /**
   * 问询文件生成路径
   */
  public static final String DOCX_PATH = "docxPath";
  /**
   * 问询文件临时路径
   */
  public static final String TEMP_DOCX_PATH = "tempDocxPath";
  /**
   * html文件生成路径
   */
  public static final String HTML_PATH = "htmlPath";
  /**
   * 图片生成路径
   */
  public static final String IMAGE_PATH = "imageDir";

  /**
   * 数据库里默认的version值
   */
  public static final Integer DEFAULT_VERSION = 0;

  /**
   * 正则：手机号（精确）
   * <p>
   * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184
   * 、187、188、198
   * </p>
   * <p>
   * 联通：130、131、132、145、155、156、175、176、185、186、166
   * </p>
   * <p>
   * 电信：133、153、173、177、180、181、189、199
   * </p>
   * <p>
   * 全球星：1349
   * </p>
   * <p>
   * 虚拟运营商：170
   * </p>
   */
  public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
  /**
   * 正则： 身份证格式是否正确
   */
  public static final String REGEX_ID_CARD = "^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|[xX])$";
  /**
   * 正则: 邮箱格式
   */
  public static final String REGEX_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

  /**
   * 企业个人级联初始化
   */
  public static final String PARENT_CODE = "notary.type";
}
