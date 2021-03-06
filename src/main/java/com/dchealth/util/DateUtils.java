package com.dchealth.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/13.
 */
public class DateUtils {
    public static final String TIME_FORMAT_A = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_B = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_C = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String TIME_FORMAT_D = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TELCORDIA_DATE_FORMAT = "MM-dd-yyyy";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String TIME_FORMAT_SYSTEM = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String DATE_FORMAT_B   = "yyMMdd";
    private DateUtils(){
    }
    public static Timestamp convertStringToDate(String date){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }
    public static String getFormatDate(Date date, String format) {
        String dateStr = null;
        try {
            if (date != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                dateStr = simpleDateFormat.format(date);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateStr;
    }

    public static Date convertDate(String dateStr, String format) {
        java.util.Date date = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }


    public static Date convertDate(String dateStr, String format,Locale locale) {
        java.util.Date date = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format,locale);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }
    public static Date convertDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr.trim())) {
            return null;
        }
        java.util.Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                DateUtils.TIME_FORMAT_A);
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException ex) {
            simpleDateFormat = new SimpleDateFormat(
                    DateUtils.DATE_FORMAT);
            try {
                date = simpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
                try {
                    date = new Date(Long.valueOf(dateStr));
                } catch (Exception ec) {
                    try {
                        date = simpleDateFormat.parse("1999-01-01");
                    } catch (ParseException e1) {
                        System.out.println("????????????" + dateStr + "?????????????????????");
                    }
                }
            }
        }
        return date;
    }

    public static Date checkIsDate(String dateStr) {
        java.util.Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                DateUtils.TIME_FORMAT_A);
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException ex) {
            simpleDateFormat = new SimpleDateFormat(
                    DateUtils.DATE_FORMAT);
            try {
                date = simpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
                try {
                    date = new Date(Long.valueOf(dateStr));
                } catch (Exception ec) {
                    System.out.println("????????????" + dateStr + "?????????????????????");
                }
            }
        }
        return date;
    }
    public static java.sql.Timestamp getFormatTimestamp(String dateStr) {
        String format = getTimeFormat(dateStr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        java.util.Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("????????????????????????");
        }
        java.sql.Timestamp timestamp = getFormatTimestamp(date, format);
        return timestamp;
    }
    public static String getTimeFormat(String dateStr) {
        String timeFormat = TIME_FORMAT_A;
        if (dateStr != null) {
            String[] str1 = dateStr.split(":");
            String[] str2 = dateStr.split("-");
            boolean existDot = dateStr.contains(".");
            if (str1.length == 3 && str2.length == 3) {
                if(!existDot) {
                    timeFormat = TIME_FORMAT_A;
                } else {
                    timeFormat = TIME_FORMAT_D;
                }
            }
            else if (str1.length == 1 && str2.length == 3) {
                timeFormat = DATE_FORMAT;
            }
            else if (dateStr.length() == 14) {
                timeFormat = TIME_FORMAT_B;
            }
            else if (dateStr.length() == 6) {
                timeFormat = DATE_FORMAT_B;
            }
            else if (dateStr.length() == 4) {
                timeFormat = YEAR_FORMAT;
            }
            else if (str1.length == 4){
                timeFormat = TIME_FORMAT_C;
            }
        }
        System.out.println("timeFormat is:"+timeFormat);
        return timeFormat;
    }
    public static java.sql.Timestamp getFormatTimestamp(java.util.Date date, String format){
        java.sql.Timestamp timestamp = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String resStr = simpleDateFormat.format(date);
        timestamp = java.sql.Timestamp.valueOf(resStr);
        return timestamp;
    }
}
