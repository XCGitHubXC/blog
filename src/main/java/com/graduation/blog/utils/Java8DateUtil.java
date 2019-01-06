package com.graduation.blog.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zli on 2017/6/21.
 *
 * 日期工具
 */
public class Java8DateUtil {

  public static final String DATE_FORMAT = "yyyy-MM-dd";

  public static final String TIME_FORMAT = "HH:mm:ss";

  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /**
   * 00：00：00
   */
  public static final String START_TIME = " 00:00:00";
  /**
   * 59:59:59
   */
  public static final String END_TIME = " 23:59:59";

  /**
   * 获取日期
   */
  public static Date getDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDateTime.atZone(zone).toInstant();
    return Date.from(instant);
  }

  public static Date getDate(LocalDate localDate) {
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
    return Date.from(instant);
  }

  public static Date getDate(LocalDateTime localDateTime) {
    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zdt = localDateTime.atZone(zoneId);
    return Date.from(zdt.toInstant());
  }

  public static LocalDateTime getLocalDateTime(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.parse(date, formatter);
  }

  public static LocalDateTime getLocalDateTime(Date date) {
    Instant instant = date.toInstant();
    ZoneId zone = ZoneId.systemDefault();
    return LocalDateTime.ofInstant(instant, zone);
  }

  public static LocalDate getLocalDate(Date date) {
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
    return instant.atZone(zoneId).toLocalDate();
  }

  /**
   * @param dateStr yyyy-MM-dd 格式
   */
  public static LocalDate getLocalDate(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(dateStr, formatter);
  }

  public static void main(String[] args) {
  }

  /***
   * 返回当前年，如：2015
   *
   * @return
   */
  public static String getCurrentYear() {
    return String.valueOf(LocalDate.now().getYear());
  }

  /***
   * 返回当前月,如：07
   *
   * @return
   */
  public static String getCurrentMonth() {
    String month = String.valueOf(LocalDate.now().getMonthValue());
    if (month.length() == 1) {
      return "0" + month;
    }
    return month;
  }

  /***
   * 返回当前日,如：26
   *
   * @return
   */
  public static String getCurrentDayOfMonth() {
    String day = String.valueOf(LocalDate.now().getDayOfMonth());
    if (day.length() == 1) {
      return "0" + day;
    }
    return day;
  }

  /**
   * 格式化
   *
   * @param date 日期
   * @param pattern 格式化格式
   * @return string型日期
   */
  public static String formatter(Date date, String pattern) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime localDateTime = getLocalDateTime(date);
    return localDateTime.format(dateFormatter);
  }

  /**
   * 转换时间
   */
  public static String getDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    String yearStr = calendar.get(Calendar.YEAR) + "";
    int month = calendar.get(Calendar.MONTH) + 1;
    String monthStr = month < 10 ? "0" + month : month + "";
    int day = calendar.get(Calendar.DATE);
    String dayStr = day < 10 ? "0" + day : day + "";
    return yearStr + "年" + monthStr + "月" + dayStr + "日";
  }

  public static String getYear() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar.get(Calendar.YEAR) + "";
  }

  public static Date addDate(Date time, Integer day) {
    Calendar calendar = Calendar.getInstance();
    if (time == null) {
      calendar.setTime(new Date());
    } else {
      calendar.setTime(time);
    }
    calendar.add(Calendar.DATE, day);
    return calendar.getTime();
  }

  public static String addDateToString(Date time, Integer day) {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    Calendar calendar = Calendar.getInstance();
    if (time == null) {
      calendar.setTime(new Date());
    } else {
      calendar.setTime(time);
    }
    calendar.add(Calendar.DATE, day);
    return sdf.format(calendar.getTime());
  }

  /**
   * 判断时间是否应该高亮
   */
  public static boolean isHighlight(Date endDate) {
    if (endDate == null) {
      return false;
    }
    Calendar subTwoDate = Calendar.getInstance();
    subTwoDate.setTime(endDate);
    subTwoDate.add(Calendar.DATE, -2);
    Calendar endTime = Calendar.getInstance();
    endTime.setTime(endDate);

    Calendar current = Calendar.getInstance();
    current.setTime(new Date());

    boolean flag = current.compareTo(subTwoDate) == 1 || current.compareTo(subTwoDate) == 0;
    boolean flagEnd = current.compareTo(endTime) == -1 || current.compareTo(endTime) == 0;
    if (current.compareTo(subTwoDate) == -1) {
      return false;
    } else if (flag && flagEnd) {
      return true;
    }
    return false;
  }

  /**
   * 时间date1 比较 date2 date1比date2早
   */
  public static boolean compareWith(Date date1, Date date2) {
    if (date1 == null) {
      return false;
    }
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTime(date1);

    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTime(date2);

    if (calendar1.compareTo(calendar2) == -1) {
      return true;
    }
    return false;
  }

}
