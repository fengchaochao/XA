package com.prj.utils;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import com.unionpay.acp.sdk.LogUtil;

/**
 * 工具类
 *
 * @author caobo
 */
public class Tool {


    private static long lastClickTime;

    public static boolean needBlockThisClickEvent() {
        if (System.currentTimeMillis() - lastClickTime > 800) {
            lastClickTime = System.currentTimeMillis();
            return false;
        }

        return true;
    }


    /**
     * 根据时间戳来解析时间
     *
     * @return
     */
    public static String timeStampDate(Long timestamp) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(timestamp));
        return date;
    }



    /**
     * 判断指定位置的文件是否存在
     *
     * @param filepath
     * @param filename zl
     */
    public static boolean isFileExist(String filepath, String filename) {
        File file = new File(filepath + filename);
        return file.exists();
    }

    /**
     * 删除指定路径文件夹内的文件(除了dat序列化文件)
     */
    public static boolean delFiles(String fileFodlerpath) {
        File fileFolder = new File(fileFodlerpath);
        if (fileFolder.exists()) {
            File[] files = fileFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getAbsolutePath();
                    if (!fileName.endsWith("dat")) {
                        file.delete();
                    }
                }
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("deprecation")
	public static String simpleFormatDateTime(String datetime) {
        GregorianCalendar calender = new GregorianCalendar();
        TimeZone timezone = TimeZone.getDefault();

        String timeString = datetime ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date creatDate = null;
        try {
            creatDate = sdf.parse(timeString);
            calender.setTimeZone(timezone);

            if (calender.getTime().getYear() != creatDate.getYear()) {
                SimpleDateFormat daySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                daySimpleDateFormat.setTimeZone(timezone);
                return daySimpleDateFormat.format(creatDate);
            } else {
                SimpleDateFormat monthSimpleDateFormat = new SimpleDateFormat("MM-dd");
                monthSimpleDateFormat.setTimeZone(timezone);
                return monthSimpleDateFormat.format(creatDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat daySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        daySimpleDateFormat.setTimeZone(timezone);
        return daySimpleDateFormat.format(creatDate);

    }

    //判断是否是同一天
    @SuppressWarnings("deprecation")
	public static boolean isEquDay(String time1, String time2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            int day1 = sdf.parse(time1).getDay();
            int day2 = sdf.parse(time2).getDay();
            if (day1 == day2) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    static HashMap<String, String> dateFormatCache = new HashMap<String, String>();
    static HashMap<String, Long> dateFormateUpdatedTime = new HashMap<String, Long>();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat timeSimpleDateFormat = new SimpleDateFormat("HH:mm");
    static SimpleDateFormat daySimpleDateFormat = new SimpleDateFormat("MM-dd");
    static SimpleDateFormat monthSimpleDateFormat = new SimpleDateFormat("MM-dd");
    static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressWarnings({ "unused", "deprecation" })
	public static String formatDateTime(String datetime) {
        if (dateFormatCache.containsKey(datetime)) {
            if (dateFormateUpdatedTime.get(datetime) - System.currentTimeMillis() > -60 * 1000) {
                return dateFormatCache.get(datetime);
            }
        }
        String result;
        String timeString = datetime;
        try {
            GregorianCalendar calender = new GregorianCalendar();

            TimeZone timezone = TimeZone.getDefault();

            Date creatDate = sdf.parse(timeString);

            Date today = calender.getTime();

            calender.add(Calendar.DAY_OF_YEAR, -1);
            Date yesterDay = calender.getTime();

            long createTime = creatDate.getTime();

            calender.setTimeZone(timezone);

            long nowTime = calender.getTimeInMillis();//+Timezone.getRawOffset();

            long time = nowTime - createTime;

            long oneMinuteMills = 60 * 1000;
            long oneHourMills = 60 * oneMinuteMills;
            long halfDayMills = 12 * oneHourMills;
            long onDayMills = 2 * halfDayMills;

            //计算今天凌晨时的时间点
            long nowZore = nowTime - nowTime % onDayMills;


            timeSimpleDateFormat.setTimeZone(timezone);
            String timeComponent = timeSimpleDateFormat.format(creatDate);


            daySimpleDateFormat.setTimeZone(timezone);
            String dayComponent = daySimpleDateFormat.format(creatDate);
            monthSimpleDateFormat.setTimeZone(timezone);
            String month = monthSimpleDateFormat.format(creatDate);

            yearFormat.setTimeZone(timezone);
            String year = yearFormat.format(creatDate);


            //今年的
            if (creatDate.getYear() == today.getYear()) {
                //昨天的
                if (creatDate.getMonth() == yesterDay.getMonth() && creatDate.getDate() == yesterDay.getDate()) {
                    result = "昨天";
                } else {
                    if (creatDate.getMonth() == today.getMonth()) {//本月
                        //都是今天
                        if (creatDate.getDate() == today.getDate()) {

                            long createMils = creatDate.getTime();
                            long nowMils = today.getTime();
                            long div = nowMils - createMils;
                            if (div > 3600 * 1000) {
                                result = (int) (div / 3600 / 1000) + "小时前";
                            } else if (div > 60 * 1000) {
                                result = (int) (div / 60 / 1000) + "分钟前";
                            } else {
                                result = "刚刚";
                            }
                        } else {//其他天
                            result = dayComponent;
                        }
                    } else {
                        //其他月份
                        result = month;
                    }
                }
            } else {
                //其他年
                result = year;
            }

        } catch (ParseException e) {
//            LogUtil.error("parse date fault", e);
            result = datetime;
        }

        dateFormatCache.put(datetime, result);
        dateFormateUpdatedTime.put(datetime, System.currentTimeMillis());
        return result;
    }

    /**
     * 将传入的毫秒转换为hh:mm:ss
     *
     * @param milliseconds 毫秒
     * @return
     */
    public static String formatData(int milliseconds) {
        StringBuffer buffer = new StringBuffer();
        int hour, min, sec;
        LogUtil.debug("milliseconds==" + milliseconds);
        sec = milliseconds / 1000;
        if (sec < 60) {
            //不到一分钟
            buffer.append("00:" + formatTime(sec));
        } else {
            //超过1分钟
            min = sec / 60;
            sec = sec % 60;
            if (min < 60) {
                //不到1小时
                buffer.append(formatTime(min) + ":" + formatTime(sec));
            } else {
                //超过1小时
                hour = min / 60;
                min = min % 60;
                buffer.append(formatTime(hour) + ":" + formatTime(min) + ":" + formatTime(sec));
            }
        }
        return buffer.toString();
    }

    /**
     * 传入数值为各位数字时，前加0
     *
     * @param hms 小时，或分钟，或秒
     * @return
     */
    private static String formatTime(int hms) {
        if (hms < 10) {
            return "0" + hms;
        } else {
            return "" + hms;
        }
    }

    public static int getInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int getInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public static void main(String[] args) {
		System.out.println(Tool.formatDateTime("2017-08-22 14:23:00"));
	}
}