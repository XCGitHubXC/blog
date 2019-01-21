package com.graduation.blog.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 * 加密工具类
 *
 * md5加密出来的长度是32位 add Aes加密算法
 *
 * @author upate by ditt
 * @date 2018/6/19 下午2:08
 */
@Slf4j
public class Encrypt {

  private static final String IV_STRING = "a-16-Byte-String";

  private static final String KEY = "1$S#D;@GWVX$vScs";

  private static final String CHARSET = "UTF-8";

  /**
   * 测试
   */
  public static void main(String[] args) {
    // md5加密测试
    String md51 = md5("1111");
    String md52 = md5("中国");

  }

  /**
   * md5加密
   */
  public static String md5(String inputText) {
    return encrypt(inputText, "md5");
  }

  public static String sha(String inputText) {
    return encrypt(inputText, "sha-1");
  }

  public static String aesEncrypt(String inputText) {
    String encryptText = null;
    try {
      encryptText = aesEncryptString(inputText, KEY);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return encryptText;
  }

  public static String aesDecrypt(String inputText) {
    String decryptText = null;
    try {
      decryptText = aesDecryptString(inputText, KEY);
      return decryptText;
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return decryptText;
  }


  /**
   * md5或者sha-1加密
   *
   * @param inputText 要加密的内容
   * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
   */
  private static String encrypt(String inputText, String algorithmName) {
    if (inputText == null || "".equals(inputText.trim())) {
      throw new IllegalArgumentException("请输入要加密的内容");
    }
    String encryptText = null;
    try {
      MessageDigest m = MessageDigest.getInstance(algorithmName);
      m.update(inputText.getBytes(CHARSET));
      byte s[] = m.digest();
      return hex(s);
    } catch (NoSuchAlgorithmException e) {
      log.error("Encrypt encrypt error", e);
    } catch (UnsupportedEncodingException e) {
      log.error("Encrypt encrypt error", e);
    }
    return encryptText;
  }

  /**
   * 返回十六进制字符串
   */
  private static String hex(byte[] arr) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arr.length; ++i) {
      sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
  }

  /**
   * @param content aes加密内容
   */
  public static String aesEncryptString(String content, String key)
      throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    byte[] contentBytes = content.getBytes(CHARSET);
    byte[] keyBytes = key.getBytes(CHARSET);
    byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
    Encoder encoder = Base64.getEncoder();
    return encoder.encodeToString(encryptedBytes);
  }

  /**
   * @param content aes解密内容
   */
  public static String aesDecryptString(String content, String key)
      throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    Decoder decoder = Base64.getDecoder();
    byte[] encryptedBytes = decoder.decode(content);
    byte[] keyBytes = key.getBytes(CHARSET);
    byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
    return new String(decryptedBytes, CHARSET);
  }

  public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
  }

  public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
  }

  private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

    byte[] initParam = IV_STRING.getBytes(CHARSET);
    IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(mode, secretKey, ivParameterSpec);

    return cipher.doFinal(contentBytes);
  }

}
