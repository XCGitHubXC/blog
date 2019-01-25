package com.graduation.blog.constants;

/**
 * 校验异常信息
 *
 */
public class ValidateMessage {

  /**
   * 可配合validationMessage.properties使用
   */
  public static final String MOBILE_PHONE_INCORRECT = "{mobilePhone.incorrect}";
  public static final String MOBILE_PHONE_OLD_INCORRECT = "{oldMobilePhone.incorrect}";
  public static final String PLATFORM_NOT_NULL = "{platform.notNull}";
  public static final String SMSCODE_NOTBLANK = "{smsCode.notBlank}";
  public static final String REFRESH_TOKEN_NOT_BLANK = "{refreshToken.notBlank}";
  public static final String ID_NOT_BLANK = "{id.notBlank}";
  /** 备注不能为空 */
  public static final String REMARK_CANNOT_EMPTY = "{remark.cannotEmpty}";
  /** 没有文件上传 */
  public static final String NOT_FILE_UPLOAD = "{file.notFileUpload} ";
  /** 未找到相应文件 */
  public static final String FILE_NOT_FOUND = "{file.fileNotFound}";
  /** 文件名为空 */
  public static final String FILE_NAME_IS_EMPTY = "{file.fileNameIsEmpty}";
  /** 文件大小为空 */
  public static final String FILE_SIZE_IS_EMPTY = "{file.fileSizeIsEmpty}";

  /** 传入参数为空 */
  public static final String PARAMETER_IS_NULL = "{common.parameterIsNull}";
  public static final String FAIL_FILE = "{file.fail}";

  /** 名称不能为空 */
  public static final String NAME_NOT_BLANK = "{name.notBlank}";
  /** 角色id不能为空 */
  public static final String ROLE_ID_NOT_BLANK = "{roleId.notBlank}";
  /** code不能为空 */
  public static final String CODE_NOT_BLANK = "{code.notBlank}";
  /** value不能为空 */
  public static final String VALUE_NOT_BLANK = "{value.notBlank}";
  /** 菜单正在使用 */
  public static final String MENU_BEING_USED = "{menu.beingUsed}";
  /** id不存在 */
  public static final String ID_NOT_EXISTS = "{id.notExists}";
  /** 名称已经存在 */
  public static final String NAME_EXISTS = "{name.exists}";
  /** 菜单正在使用 */
  public static final String ROLE_BEING_USED = "{role.beingUsed}";

  // 登录
  /** 用户名不能为空 */
  public static final String LOGIN_NAME_NOT_BLANK = "{loginName.notBlank}";
  /** 手机号不能为空 */
  public static final String MOBILE_PHONE_NOT_BLANK = "{mobilePhone.notBlank}";
  /** 原手机号不能为空 */
  public static final String MOBILE_PHONE_OLD_NOT_BLANK = "{oldMobilePhone.notBlank}";
  /** 用户类型不能为空 */
  public static final String USER_TYPE_NOT_BLANK = "{userType.notBlank}";
  /** 短信类型不能为空 */
  public static final String SMS_TYPE_NOT_BLANK = "{smsType.notBlank}";
  /** 登录密码不能为空 */
  public static final String PASSWORD_NOT_BLANK = "{password.notBlank}";
  /** 手机验证码不能为空 */
  public static final String PHONE_CODE_NOT_BLANK = "{phoneCode.notBlank}";
  /** 登录密码错误 */
  public static final String PASSWORD_IS_WRONG = "{password.isWrong}";
  /** 用户登录凭证 */
  public static final String WXCODE_NOT_BLANK = "{wxcode.notBlank}";

  // 注册
  /** 手机号已经注册 */
  public static final String MOBILE_EXITS = "{userMobile.exist}";
  /** 邮箱已经注册 */
  public static final String EMAIL_EXITS = "{userEmail.exist}";
  /** 用户id不能为空 */
  public static final String USER_ID_NOT_BLANK = "{userId.notBlank}";
  /** 用户名不存在 */
  public static final String USER_NAME_NOT_EXISTS = "{userName.notExists}";
  /** 邮箱不能为空 */
  public static final String EMAIL_NOT_BLANK = "{email.notBlank}";
  /** 邮箱验证码不能为空 */
  public static final String EMAIL_CODE_NOT_BLANK = "{emailCode.notBlank}";
  /** 身份证编码为空 */
  public static final String ID_CARD_NOT_BLANK = "{idCard.notBlank}";
  /** 姓名为空 */
  public static final String USER_NAME_NOT_BLANK = "{userName.notBlank}";
  /** 邮件格式不正确 */
  public static final String EMAIL_INCORRECT = "{email.incorrect}";
  /** 企业负责人不能为空 */
  public static final String RESPONSIBLE_USER_NOT_BLANK = "{responsibleUser.notBlank}";
  /** 头像不能为空 */
  public static final String IMG_PHOTO_NOT_BLANK = "{imgPhoto.notBlank}";
  /** 性别不能为空 */
  public static final String SEX_NOT_BLANK = "{sex.notBlank}";
  /** 擅长领域不能为空 */
  public static final String ABILITY_NOT_BLANK = "{ability.notBlank}";
  /** 常用机构不能为空 */
  public static final String FREQ_USED_ORG_NOT_BLANK = "{freqUsedOrg.notBlank}";
  /** 从业经历不能为空 */
  public static final String EXPERIENCE_NOT_BLANK = "{experience.notBlank}";

  // 字典表
  /** 字典表类型不能为空 */
  public static final String DICT_TYPE_NOT_BLANK = "{dictType.notBlank}";

  // 案件类型
  /**
   * 案件类型不能为空
   */
  public static final String CASETYPE_NOT_BLANK = "{case.notBlank}";
  /**
   * 纠纷类型不能为空
   */
  public static final String DISPUTETYPE_NOT_BLANK = "{disputeType.notBlank}";

  /** 案件 **/
  public static final String CASE_USER_TYPE_NOT_BLANK = "{case.userType.notBlank}";

  /**
   * 案件关联人员
   */
  public static final String CASEUSER_RELATION_NOT_NULL = "{caseuser.relation.notNull}";

  /**
   * 证据
   */
  public static final String EVIDENCE_NOT_NULL = "{evidence.notNull}";
  /**
   * 纠纷业务要素
   */
  public static final String DISPUTE_NOT_BLANK = "{dispute.notBlank}";
  /**
   * 案件关联人员
   */
  public static final String CASEBASE_NOT_NULL = "{caseBase.notNull}";
  /**
   * 文件类型有误
   */
  public static final String FILE_TYPE_IS_WRONG = "{file.fileTypeIsWrong}";
  /**
   * 文书类型
   */
  public static final String DOC_TYPE_ERROR = "{doc.type.error}";
}
