package com.graduation.blog.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTermType;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenIterator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
 * 错误信息解析，延用 @AbstractMessageInterpolator
 * 
 *
 *
 */
public class ValidateMessageParser {

  private static final String MESSAGE_KEY_PREFIX = "{";
  private static final String MESSAGE_KEY_SUFFIX = "}";
  private static final String ERROR_CODE_KEY_PRE_FIX = "errorCode.";

  private ResourceBundleLocator userResourceBundleLocator =
      new PlatformResourceBundleLocator(ResourceBundleMessageInterpolator.USER_VALIDATION_MESSAGES);


  private String getErrorCodeMessagekey(ErrorCode errorCode) {
    StringBuilder sb = new StringBuilder();
    sb.append(MESSAGE_KEY_PREFIX).append(ERROR_CODE_KEY_PRE_FIX).append(errorCode.toString())
        .append(MESSAGE_KEY_SUFFIX);
    return sb.toString();
  }

  public String parseMessage(ErrorCode errorCode, String messageKey) {
    String realMessageKey =
        StringUtils.isBlank(messageKey) || errorCode.toString().equals(messageKey)
            ? getErrorCodeMessagekey(errorCode)
            : messageKey;
    try {
      if (realMessageKey.startsWith(MESSAGE_KEY_PREFIX)
          && realMessageKey.endsWith(MESSAGE_KEY_SUFFIX)) {
        Locale locale = LocaleContextHolder.getLocale();
        return interpolateBundleMessage(realMessageKey,
            userResourceBundleLocator.getResourceBundle(locale), locale, true);
      } else {
        return realMessageKey;
      }
    } catch (Exception e) {
      log.error("validate message error :", e);
      return realMessageKey;
    }
  }


  private String interpolateBundleMessage(String message, ResourceBundle bundle, Locale locale,
      boolean recursive) throws MessageDescriptorFormatException {
    TokenCollector tokenCollector = new TokenCollector(message, InterpolationTermType.PARAMETER);
    TokenIterator tokenIterator = new TokenIterator(tokenCollector.getTokenList());
    while (tokenIterator.hasMoreInterpolationTerms()) {
      String term = tokenIterator.nextInterpolationTerm();
      String resolvedParameterValue = resolveParameter(term, bundle, locale, recursive);
      tokenIterator.replaceCurrentInterpolationTerm(resolvedParameterValue);
    }
    return tokenIterator.getInterpolatedMessage();
  }

  private String resolveParameter(String parameterName, ResourceBundle bundle, Locale locale,
      boolean recursive) throws MessageDescriptorFormatException {
    String parameterValue;
    try {
      if (bundle != null) {
        parameterValue = bundle.getString(removeCurlyBraces(parameterName));
        if (recursive) {
          parameterValue = interpolateBundleMessage(parameterValue, bundle, locale, recursive);
        }
      } else {
        parameterValue = parameterName;
      }
    } catch (MissingResourceException e) {
      // return parameter itself
      parameterValue = parameterName;
    }
    return parameterValue;
  }

  private String removeCurlyBraces(String parameter) {
    return parameter.substring(1, parameter.length() - 1);
  }
}
