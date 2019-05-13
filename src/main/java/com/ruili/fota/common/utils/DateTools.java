package com.ruili.fota.common.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class DateTools {

    /**
     * Date转String
     *
     * @param date
     * @return
     */
    public static String DateToString(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * String转Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date currentTime() {
        TimeZone time = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(time);

        return new Date();

    }

    /**
     * 生成事件编号
     * 例 devuceId 18011619
     * 格式 日期+deviceid+随机三位数  181213+18011619+123
     *
     * @param deviceid
     * @return
     */
    public static String generatorEventNum(String deviceid) {
        String date = DateToString(currentTime(), "yyyyMMdd");
        int random = (int) ((Math.random() * 9 + 1) * 100);
        return date.substring(2) + deviceid + random;

    }

    /**
     * 相差时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String dateDiff(Date startTime, Date endTime) {

        //一天的毫秒数
        long nd = 1000 * 24 * 60 * 60;
        //一小时的毫秒数
        long nh = 1000 * 60 * 60;
        //一分钟的毫秒数
        long nm = 1000 * 60;
        //一秒钟的毫秒数
        long ns = 1000;

        long diff;
        //获得两个时间的毫秒时间差异

        diff = endTime.getTime() - startTime.getTime();

        //计算差多少天
        long day = diff / nd;
        //计算差多少小时
        long hour = diff % nd / nh;
        //计算差多少分钟
        long min = diff % nd % nh / nm;
        //计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;

        return (day > 0 ? day + "天" : "") + (hour > 0 ? hour + "小时" : "") + (min > 0 ? min + "分钟" : "") + (sec > 0 ? sec + "秒" : "");

//            return day+"天"+hour+"小时"+min+"分钟"+sec+"秒";

    }


    /**
     * 获得上个月最后一天的12：00
     *
     * @return
     */
    public static String getFirstDayInAMonthFormat() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前日期
        Calendar firstDayInAMonth = Calendar.getInstance();
        firstDayInAMonth.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        firstDayInAMonth.set(Calendar.DAY_OF_MONTH, 1);
        firstDayInAMonth.set(Calendar.HOUR, -12);
        firstDayInAMonth.set(Calendar.MINUTE, 0);
        firstDayInAMonth.set(Calendar.SECOND, 0);
        String result = sm.format(firstDayInAMonth.getTime());
        return result;
    }

    /**
     * 昨天
     *
     * @return
     */
    public static String getYesterdayTimeFormat() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前日期
        Calendar firstDayInAMonth = Calendar.getInstance();
        firstDayInAMonth.add(Calendar.DATE, -1);
        String result = sm.format(firstDayInAMonth.getTime());
        return result;
    }

    /**
     * 前天
     *
     * @return
     */
    public static String getTheDayBeforeYesterdayTimeFormat() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前日期
        Calendar firstDayInAMonth = Calendar.getInstance();
        firstDayInAMonth.add(Calendar.DATE, -2);
        String result = sm.format(firstDayInAMonth.getTime());
        return result;
    }

    /**
     * DataV接口用
     *
     * @return
     */
    public static List<String> getWeekTime() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> daysList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Calendar day = Calendar.getInstance();
            day.set(Calendar.HOUR, -12);
            day.set(Calendar.MINUTE, 0);
            day.set(Calendar.SECOND, 0);
            day.add(Calendar.DATE, i - 8);
            daysList.add(sm.format(day.getTime()));
        }
        return daysList;
    }

    public static void main(String[] args) throws ParseException {
        Date date = StringToDate("2019/02/15", "yyyy/MM/dd");
        Date s = StringToDate(DateToString(StringToDate("2019/02/15", "yyyy/MM/dd"), null), null);
        System.out.println(date);
        System.out.println(s);
    }

}
