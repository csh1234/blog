package com.lrm.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Classname DateTimeUtils
 * @Description TODO
 * @Date 2021/4/26 22:26
 * @Created by Administrator
 */
public class DateTimeUtils {

    private static final String ISO_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd-HH:mm:ss";

    /**
     * 日期转成字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        String s = DateFormatUtils.format(date, ISO_DATETIME_TIME_ZONE_FORMAT);
        return s;
    }

    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = dateToString(new Date());
        System.out.println(s);
    }
}
