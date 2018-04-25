package com.zme.zlibrary.utils;

import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Time;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Zhiyahan
 * @ClassName: DateTimeUtil
 * @Description: TODO 时间处理工具类，时间格式转换，获取当前时间，计算时间差，设置
 * @date 2015年9月25日 下午2:56:25
 */
public class DatetimeUtils {

    public final static String HH_mm_ss = "HH:mm:ss";//HH:mm:ss格式
    public final static String HH_mm = "HH:mm";//HH:mm格式
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";//yyyy-MM-dd HH:mm:ss格式
    public final static String yyyyPointMMPointdd_HH_mm_ss = "yyyy.MM.dd HH:mm:ss";//yyyy-MM-dd HH:mm:ss格式
    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";//yyyy-MM-dd HH:mm格式
    public final static String yyyyMMdd_HHmmss = "yyyyMMdd_HHmmss";//yyyMMdd_HHmmss格式
    public final static String yyyyMMddPointHHmmss = "yyyyMMdd.HHmmss";//yyyyMMdd.HHmmss格式
    public final static String yyyy_MM_dd = "yyyy-MM-dd";//yyyy-MM-dd 格式
    public final static String y_M_d_H_m_s = "%Y-%m-%d %H:%M:%S";
    public final static String yyyyMMdd = "yyyy/MM/dd";//yyyy-MM-dd HH:mm:ss格式


    public static final int ss = 1 * 1000;//一秒钟的毫秒数
    public static final int mm = 60 * ss;//一分钟的毫秒数
    public static final int HH = 60 * mm;//1小时毫秒
    public static final int H24 = 24 * HH;//24小时
    public static final int H48 = 48 * HH;//48小时
    public static final int H72 = 72 * HH;//72小时
    public static long MILLIS_IN_MINUTE = 60000L;

    /**
     * @return String
     * @Title: getCurrentTime
     * @Description: TODO 获取当前时间,格式为 yyyy_MM_dd_HH_mm_ss
     */
    public static String getCurrentTime(String format) {
        // TODO Auto-generated method stub
        Time localTime = new Time(Time.getCurrentTimezone());
        localTime.setToNow();
        return localTime.format(format);
    }

    /**
     * @Title: getCurrentTimeMillis
     * @Description: TODO 获取当前的时间，单位是毫秒
     */
    public static long currentTimeMillis() {
        // TODO Auto-generated method stub
        //System.currentTimeMillis() // 从1970年1月1日 UTC到现在的毫秒数；
        return System.currentTimeMillis();
    }

    /**
     * @param datetime 时间
     * @Title: mofifyTimeByRootPermission
     * @Description: TODO 修改系统的时间 此法需要获得root权限
     */
    public static void mofifyTimeByRootPermission(String datetime) {
        try {
            //格式化时间
            String formatTime = formatTime(datetime, yyyy_MM_dd_HH_mm_ss, yyyyMMddPointHHmmss);
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(
                    process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT-8\n");
            os.writeBytes("/system/bin/date -s " + formatTime + "\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dataTime
     * @Title: mofifyTimeBySystemPermission
     * @Description: TODO  修改系统的时间 通过system权限
     */
    public static void mofifyTimeBySystemPermission(String dataTime) {
        try {
            Date date = parseDate(dataTime, yyyy_MM_dd_HH_mm_ss);
            long when = date.getTime();
            if (when / 1000 < Integer.MAX_VALUE) {
                SystemClock.setCurrentTimeMillis(when);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * @param date
     * @return ArrayList<Integer>
     * @Title: getTime
     * @Description: TODO 根据时间来获得时间的年、月、日、时、分、秒
     */
    @SuppressWarnings({"deprecation", "unused"})
    private static ArrayList<Integer> getTime(String date) {
        ArrayList<Integer> alList = new ArrayList<Integer>();
        try {
            SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
            Date end_date = format.parse(date);
            alList.add(end_date.getYear());
            alList.add(end_date.getMonth() + 1);
            alList.add(end_date.getDay());
            alList.add(end_date.getHours());
            alList.add(end_date.getMinutes());
            alList.add(end_date.getSeconds());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return alList;
    }


    /**
     * @Title: isOver
     * @param overTime   过去的时间 格式为 yyyyMMdd_HHmmss
     * @param serverTime 当前的系统时间  格式为  yyyy-MM-dd HH:mm:ss
     * @param daySum  距离天数
     * @return boolean
     * @Description: TODO 判断时间和当前时间比较是否超过指定的天数
     */
    public static boolean isTimeOver(String overTime, String serverTime, int daySum) {
        // TODO Auto-generated method stub
        try {
            if ((null != overTime && !overTime.equals(""))
                    && (null != serverTime && !serverTime.equals(""))) {
                long l = getMillisecondIn2TimeDifference(overTime,serverTime);
                int day =(int)(l / (60 * 60 * 1000 * 24));
                if (day - daySum >= 0)
                    return true;
                 else
                    return false;
            } else
                return false;
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e("isTimeOver==" + e.getMessage());
            return false;
        }
    }


    /**
     * @param timeString
     * @param bFormatStyle 转化前的时间格式
     * @param aFormatStyle 要转化的时间格式
     * @return String
     * @Title: formatTime
     * @Description: TODO 转换时间的显示格式
     */
    public static String formatTime(String timeString, String bFormatStyle, String aFormatStyle) {
        // TODO Auto-generated method stub
        String serverTime = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(bFormatStyle);
            //把String的时间按照bFormatStyle转化成Date
            Date date = format.parse(timeString);
            SimpleDateFormat afterDateFormat = new SimpleDateFormat(aFormatStyle);
            //把时间按照aFormatStyle形式格式化
            serverTime = afterDateFormat.format(date);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e("DateTimeUtil---formatTime--" + e.getMessage());
        }
        return serverTime;
    }

    /**
     * @Title: parseDate
     * @Description: TODO 把时间转化成另一种时间格式
     * @param formatStle 时间格式
     * @return Date
     */
    public static Date parseDate(String time, String formatStle) {
        Date datetime = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStle);
            datetime = format.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datetime;
    }


    /**
     * @Description TODO 是否是相同的时间
     * @param date1
     * @param date2
     * @return boolean
     */
    public boolean isSameIn2Time(long date1, long date2) {
        long minute1 = date1 / MILLIS_IN_MINUTE;
        long minute2 = date2 / MILLIS_IN_MINUTE;
        return minute1 == minute2;
    }


    /**
     * @param beforeTime
     * @param afterTime
     * @Description TODO 获取两个时间间隔的毫秒数
     * @return int 天数
     */
    public static long getMillisecondIn2TimeDifference(String beforeTime, String afterTime) {
        long millisencond = 0;
        try {
            if (!TextUtils.isEmpty(beforeTime) && !TextUtils.isEmpty(afterTime)) {
                Date date1 = parseDate(beforeTime, yyyy_MM_dd_HH_mm_ss);
                Date date2 = parseDate(afterTime, yyyy_MM_dd_HH_mm_ss);
                //获得时间差
                millisencond = date2.getTime() - date1.getTime();
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e("getMillisecondIn2TimeDifference==" + e.getMessage());
        }
        return millisencond;
    }



    /**
     * @param time
     * @Description TODO 距离现在过去多长时间
     * @return long 毫秒
     */
    public static long overTime(String time) {
        long millisencond = 0;
        try {
            if (!TextUtils.isEmpty(time)) {
                Date date = parseDate(time, yyyy_MM_dd_HH_mm_ss);
                //获得时间差
                millisencond = System.currentTimeMillis()- date.getTime();
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e("getMillisecondIn2TimeDifference==" + e.getMessage());
        }
        return millisencond;
    }

    /**
     * @param beforeTime
     * @param afterTime
     * @Description TODO 获取两个时间间隔的天数
     * @return int 天数
     */
    public static int getDayIn2TimeDifference(String beforeTime, String afterTime) {
        int day = 0;
        long millisencond=getMillisecondIn2TimeDifference(beforeTime,afterTime);
        long hour = millisencond/ (60 * 60 * 1000);
        day = (int) hour / 24;
        LogUtils.e("getMillisecondIn2TimeDifference==" + day);

        return day;
    }


    /**
     * 对比时间 距离现在过去多长时间
     * * 时间规则
     * 刚刚：<=1分钟
     * XX分钟：<1小时
     * XX小时：<24小时
     * 昨天：>=24小时 ，<48小时
     * 前天：>=48小时，<72小时
     * yy-MM-dd HH:mm：>=72小时
     * @param time
     */
    public static String passByTime(String time) {
        String timeString = "";
        try {
            //获得时间差
            long l =overTime(time);
            if (l<=0){
                return timeString;
            }
            long hour = l / (60 * 60 * 1000);
            long min = ((l / (60 * 1000)) - hour * 60);
            long s = (l / 1000 - hour * 60 * 60 - min * 60);
            if (l <= mm) {
                timeString = "刚刚";
            } else if (l > mm && l < HH) {
                timeString = (int) min + "分钟";
            } else if (l >= HH && l < H24) {
                timeString = (int) hour + "小时";
            } else if (l >= H24 && l < H48) {
                timeString = "昨天";
            } else if (l >= H48 && l < H72) {
                timeString = "前天";
            } else if (l >= H72) {
                timeString = time;
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e("dealWithTime==" + e.getMessage());
        }
        return timeString;

    }




    /**
     * 获取两个时间天数
     * @param beforeTime
     * @param afterTime
     */
    public static int getDay(String beforeTime, String afterTime, String format) {
        int day = 0;
        try {
            if (!TextUtils.isEmpty(beforeTime) && !TextUtils.isEmpty(afterTime)) {
                Date date1 = parseDate(beforeTime, format);
                Date date2 = parseDate(afterTime, format);
                //获得时间差
                long l = date2.getTime() - date1.getTime();
                long hour = l / (60 * 60 * 1000);
                day = (int) hour / 24;
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e("getDay--" + e.getMessage());
        }
        return day;

    }


    /**
     * @param date
     * @Title: getTime
     * @Description: TODO 出生年月日计算本人年龄
     * @return age 年龄
     */
    public static int getAge(String date) {
        int age = 21;
        if (TextUtils.isEmpty(date)) {
            return age;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
            Date birthdayDate = format.parse(date);
            Date nowDate = new Date();
            age = nowDate.getYear() - birthdayDate.getYear();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LogUtils.i("DateTimeUtil>>>>>getAge>>>" + age);
        return age;
    }


    /**
     * 判断当前时间是否在某个时间段内
     *
     * @param begintime   开始时间
     * @param endtime     结束时间
     * @param currenttime 当前时间
     * @return true 在这个时间段内，否则不再这个时间段内
     */
    public static boolean betweenTime(String begintime, String endtime, String currenttime) {
        try {
            if (!TextUtils.isEmpty(begintime) && !TextUtils.isEmpty(endtime) && !TextUtils.isEmpty(currenttime)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
                Date d1 = dateFormat.parse(begintime);
                Date d2 = dateFormat.parse(endtime);
                Date d3 = dateFormat.parse(currenttime);
                long time1 = d1.getTime();
                long time2 = d2.getTime();
                long time3 = d3.getTime();
                if (time3 >= time1 && time3 <= time2) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
