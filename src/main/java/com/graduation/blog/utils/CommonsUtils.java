package com.graduation.blog.utils;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class CommonsUtils {

  private static final int RANDOM_MIN = 100000;
  private static final int RANDOM_MAX = 900000;

  /**
   * <pre>
   * 创建目录 如果存在则忽略,不存在则创建 创建失败则抛出异常{@link }
   * 
   * @param dirPath
   * @throws SecurityException
   *           If a security manager exists and its <code>{@link
   *          SecurityManager#checkRead(String)}</code>
   *           method does not permit verification of the existence of the named
   *           directory and all necessary parent directories; or if the <code>
   *           {@link
   *          SecurityManager#checkWrite(String)}</code>
   *           method does not permit the named directory and all necessary
   *           parent directories to be created
   * 
   *           AppException 当输入参数为空或空字符串
   */
  public static void createDirs(String dirPath) {
    Assert.isNotEmpty(dirPath, ErrorCode.ILLEGAL_PARAMETER);
    File f = new File(dirPath);
    if (!f.exists()) {
      f.mkdirs();
    }
  }

  /**
   * 获取一个不包含'-'符号的32位长度的UUID
   * 
   * @return
   */
  public static String get32BitUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * <PRE>
   * 将文件大小单位转换成带单位的形式响应给调用者,并保留2为小数 最大单位为GB eg: 1.00B 2.30KB 2.30MB 2.30GB
   * 
   * @param size
   * @return
   */
  public static String getPrintSize(String size) {
    Assert.isNumeric(size, ErrorCode.ILLEGAL_PARAMETER);
    long sizeLong = Long.valueOf(size);
    return getPrintSize(sizeLong);
  }

  public static boolean isNumeric(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * <PRE>
   * 将文件大小单位转换成带单位的形式响应给调用者,并保留2为小数 最大单位为GB eg: 1.00B 2.30KB 2.30MB 2.30GB
   * 
   * @param size
   * @return
   */
  public static String getPrintSize(long size) {
    BigDecimal newBig = new BigDecimal(size);
    BigDecimal big1024 = BigDecimal.valueOf(1024);
    // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
    if (big1024.compareTo(newBig) > -1) {
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "B";
    } else {
      newBig = newBig.divide(big1024);
    }
    // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
    // 因为还没有到达要使用另一个单位的时候
    // 接下去以此类推
    if (big1024.compareTo(newBig) > -1) {
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "KB";
    } else {
      newBig = newBig.divide(big1024);
    }
    if (big1024.compareTo(newBig) > -1) {
      // 因为如果以MB为单位的话，要保留最后1位小数，
      // 因此，把此数乘以100之后再取余
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "MB";
    } else {
      // 否则如果要以GB为单位的，先除于1024再作同样的处理
      newBig = newBig.divide(big1024);
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "GB";
    }
  }

  /**
   * 验证手机号（精确）
   *
   * @param input
   *          待验证文本
   * @return {@code true}: 匹配<br>
   *         {@code false}: 不匹配
   */
  public static boolean isMobileExact(CharSequence input) {
    return isMatch(BlogConst.REGEX_MOBILE_EXACT, input);
  }

  private static boolean isMatch(String regex, CharSequence input) {
    return input != null && input.length() > 0 && Pattern.matches(regex, input);
  }

  /**
   * 验证码规则：6位有效数字
   * 
   * @return
   */
  public static String generateSMSCode() {
    Random r = new Random();
    int code = RANDOM_MIN + r.nextInt(RANDOM_MAX);
    return String.valueOf(code);
  }

  /**
   * 通过身份证号获取性别
   * 
   * @param idCard
   * @return 男/女
   */
  public static String getSexByIdCard(String idCard) {
    String result = "";
    try {

      idCard = idCard.trim();
      int idxSexStart = 16;
      // 如果是15位的证件号码
      if (idCard.length() == 15) {
        idxSexStart = 14;
      }
      // 性别
      String idxSexStr = idCard.substring(idxSexStart, idxSexStart + 1);
      int idxSex = Integer.parseInt(idxSexStr) % 2;
      result = (idxSex == 1) ? "男" : "女";
    } catch (Exception e) {

    }
    return result;
  }

  public static String generateSerival(Long length, Long number) {
    int len = length.intValue();
    NumberFormat nf = NumberFormat.getInstance();
    nf.setGroupingUsed(false);
    nf.setMinimumIntegerDigits(len);
    return nf.format(number + 1).toString();
  }
}
