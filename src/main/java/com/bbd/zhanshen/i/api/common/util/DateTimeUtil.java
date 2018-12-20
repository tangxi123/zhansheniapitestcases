package com.bbd.zhanshen.i.api.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期的零点零分零秒
     * @return
     */
    public static String getCurrentStartOfDay(){
        return LocalDate.now().atStartOfDay().format(DATE_TIME_FORMATTER);
    }

    /**
     * 获取所有日期的初始时间
     * @return
     */
    public static String getAllDateBeginStartOfDay(){
        return LocalDate.of(2009,11,01).atStartOfDay().format(DATE_TIME_FORMATTER);
    }

    /**
     * 获取明天的零点零分零秒
     * @return
     */
    public static String getTomorrowStartOfDay(){
        return LocalDate.now().atStartOfDay().plusDays(1).format(DATE_TIME_FORMATTER);
    }

    /**
     * 获取昨天的零点零分零秒
     * @return
     */
    public static String getYesterdayStartOfDay(){
        return LocalDate.now().atStartOfDay().minusDays(1).format(DATE_TIME_FORMATTER);
    }

    /**
     * 获取本周第一天的零点零分零秒
     * @return
     */
    public static String getWeekStartOfDay(){
        return LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay().format(DATE_TIME_FORMATTER);
    }



    public static void main(String[] args) {
        System.out.println(getCurrentStartOfDay());
        System.out.println(getAllDateBeginStartOfDay());
        System.out.println(getTomorrowStartOfDay());
        System.out.println(getYesterdayStartOfDay());
        System.out.println(getWeekStartOfDay());
    }
}
