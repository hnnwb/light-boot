package com.light.lightboot.common.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.TemporalAccessorUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>时间工具类</p>
 *
 * @author wb
 * @Date: 2022/02/24/
 */
public class TimeUtil {

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 时间戳转Date
     * @param timestamp
     * @return
     */
    public static Date toDate(Long timestamp) {
        return DateUtil.date(timestamp);
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * localDateTime转LocalDate
     * @param localDateTime
     * @return
     */
    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 时间戳转LocalDate
     * @param timestamp
     * @return
     */
    public static LocalDate toLocalDate(Long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date转LocalDateTime,使用默认时区
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date){
        return LocalDateTimeUtil.of(date);
    }

    /**
     * localDate转LocalDateTime,使用默认时区
     * @param localDate
     * @return
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate){
        return LocalDateTimeUtil.of(localDate);
    }

    /**
     * 时间戳转LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime toLocalDateTime(Long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 时间字符串转LocalDateTime,格式要跟传入的字符串时间一致
     * @param dateStr 时间字符串
     * @param format 格式
     * @return
     */
    public static LocalDateTime toLocalDateTime(String dateStr,String format){
        return LocalDateTimeUtil.parse(dateStr,format);
    }

    /**
     * 时间字符串转LocalDateTime,格式要跟传入的字符串时间一致
     * @param dateStr 时间字符串
     * @param format 格式
     * @return
     */
    public static LocalDateTime toLocalDateTime(String dateStr,DateTimeFormatter format){
        return LocalDateTimeUtil.parse(dateStr, format);
    }


    /**
     * Date转时间字符串
     * @param date 时间
     * @param format 格式
     * @return
     */
    public static String toDateStr(Date date,String format){
        return DateUtil.format(date, format);
    }

    /**
     * LocalDate转时间字符串
     * @param localDate 时间
     * @param format 格式
     * @return
     */
    public static String toDateStr(LocalDate localDate,String format){
        return LocalDateTimeUtil.format(localDate, format);
    }

    /**
     * LocalDateTime转时间字符串
     * @param localDateTime 时间
     * @param format 格式
     * @return
     */
    public static String toDateStr(LocalDateTime localDateTime,String format){
        return LocalDateTimeUtil.format(localDateTime, format);
    }

    /**
     * LocalTime转时间字符串
     * @param localTime 时间
     * @param format 格式
     * @return
     */
    public static String toDateStr(LocalTime localTime,String format){
        return TemporalAccessorUtil.format(localTime, format);
    }

    /**
     * 是否在某个时间段内
     * @param date 目标日期
     * @param startDate 开始日期
     * @param endDate 截止日期. 当为null时, 只要 date > startDate 则返回true.
     * @return
     */
    public static boolean isBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
        if (date == null || startDate == null) {
            return false;
        }

        return !date.isBefore(startDate) && (endDate == null || !date.isAfter(endDate));
    }

    /**
     * 是否在某个时间段内
     * @param time 目标时间
     * @param startTime 开始时间
     * @param endTime 截止时间. 当为null时, 只要 time > startTime 则返回true.
     * @return
     */
    public static boolean isBetween(LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime) {
        if (time == null || startTime == null) {
            return false;
        }

        return !time.isBefore(startTime) && (endTime == null || !endTime.isAfter(time));
    }


    /**
     * 获取日期区间的所有日期. 包含首尾日期.
     * @param startDate 起始日期
     * @param endDate 截止日期
     * @return 日期区间列表
     */
    public static List<LocalDate> getBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return Collections.emptyList();
        }

        if (startDate.isAfter(endDate)) {
            return Collections.emptyList();
        }

        List<LocalDate> result = new LinkedList<>();
        LocalDate tmpDate = startDate;
        while (!tmpDate.isAfter(endDate)) {
            result.add(tmpDate);
            tmpDate = tmpDate.plusDays(1);
        }
        return result;
    }

    /**
     * 获取日期区间的所有日期. 包含首尾日期.
     * @param startDate 起始日期
     * @param endDate 截止日期
     * @return 日期区间列表
     */
    public static List<LocalDateTime> getBetween(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return Collections.emptyList();
        }

        if (startDate.isAfter(endDate)) {
            return Collections.emptyList();
        }

        List<LocalDateTime> result = new LinkedList<>();
        LocalDateTime tmpDate = startDate;
        while (!tmpDate.isAfter(endDate)) {
            result.add(tmpDate);
            tmpDate = tmpDate.plusDays(1);
        }
        return result;
    }

    /**
     * 获取两个时间相差秒数
     * @param startDateTime 开始时间
     * @param endDateTime 结束时间
     * @return
     */
    public static long getBetweenSeconds(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration between = Duration.between(startDateTime, endDateTime);
        return between.getSeconds();
    }

    /**
     * 获取两个时间相差秒数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static long getBetweenSeconds(LocalTime startTime, LocalTime endTime) {
        Duration between = Duration.between(startTime, endTime);
        return between.getSeconds();
    }

    /**
     * 分秒置0
     * @param dateTime 目标时间
     * @return
     */
    public static LocalDateTime resetMinSecToZero(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.minusSeconds(dateTime.getMinute() * 60 + dateTime.getSecond());
    }


    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
         Date date = new Date();
         DateTime dateTime = new DateTime();
        System.out.println(TimeUtil.toDateStr(now,"HH时mm分ss秒"));
        System.out.println(TimeUtil.toDateStr(localDateTime,"HH时mm分ss秒"));
        System.out.println(TimeUtil.toDateStr(localDate,"HH时mm分ss秒"));
    }

}
