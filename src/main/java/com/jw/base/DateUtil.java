package com.jw.base;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 */
public final class DateUtil {

    /**
     * Private Constructor
     **/
    private DateUtil() {
    }

    /**
     * 获取当前时间
     *
     * @return Timestamp对象
     */
    public static Timestamp getCurrontTime() {
        Timestamp sqlTimestamp = new Timestamp(new Date().getTime());
        return sqlTimestamp;
    }

    /**
     * 将Date类型转换成String类型
     *
     * @param date Date对象
     * @return 形如:"yyyy-MM-dd HH:mm:ss"
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将Date按格式转化成String
     *
     * @param date    Date对象
     * @param pattern 日期类型
     * @return String
     */
    public static String date2String(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将String类型转换成Date类型
     *
     * @param date Date对象
     * @return
     */
    public static Date string2Date(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取某日期N天后的日期
     *
     * @param datestr
     * @param day
     * @return
     */
    public static Date getBeforeAfterDate(String datestr, int day) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        java.sql.Date olddate = null;
        try {
            df.setLenient(false);
            olddate = new java.sql.Date(df.parse(datestr).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("日期转换错误");
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(olddate);

        int Year = cal.get(Calendar.YEAR);
        int Month = cal.get(Calendar.MONTH);
        int Day = cal.get(Calendar.DAY_OF_MONTH);

        int NewDay = Day + day;

        cal.set(Calendar.YEAR, Year);
        cal.set(Calendar.MONTH, Month);
        cal.set(Calendar.DAY_OF_MONTH, NewDay);

        return new Date(cal.getTimeInMillis());
    }

    /**
     * 计算两个日期差的天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysBetween(Date fDate, Date oDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(oDate);
        cReturnDate.setTime(fDate);
        cNow.set(Calendar.HOUR_OF_DAY, 0);
        cNow.set(Calendar.MINUTE, 0);
        cNow.set(Calendar.SECOND, 0);
        cReturnDate.set(Calendar.HOUR_OF_DAY, 0);
        cReturnDate.set(Calendar.MINUTE, 0);
        cReturnDate.set(Calendar.SECOND, 0);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return (int) (intervalMs / (1000 * 86400));
    }

    /**
     * @return
     * @Description: 获取当前日期的前一天
     * @ReturnType String
     * @author: liyl
     * @Created 2015年11月13日 下午5:11:14
     */
    public static Date currentBeforeDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @return
     * @Description: 获取当前日期的后一天
     * @ReturnType Date
     * @author: liyl
     * @Created 2015年11月13日 下午5:14:54
     */
    public static Date currentNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取时间的星期
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"SUND_FLAG", "MOND_FLAG", "TUES_FLAG", "WEDN_FLAG", "THUR_FLAG", "FRID_FLAG", "SATU_FLAG"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 时间比大小
     *
     * @param DATE1
     * @param DATE2
     * @param pattern
     * @return
     */
    public static int compareDate(String DATE1, String DATE2, String pattern) {

        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 在一个时间上加上或减去分钟
     *
     * @param date long
     * @param i    int
     * @return Date
     */
    public static Date addOrMinusMinutes(Date date, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.MINUTE, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 转换时间格式
     * 获取开始时间TimeStamp格式
     * @param date
     * @return
     */
    public static Timestamp getStartTimeStamp(String date) throws ParseException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }else if(date.length()<=10)
        {
            date+=" 00:00:00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.parse(date);
        return Timestamp.valueOf(date);
    }

    /**
     * 获取结束时间TimeStamp时间格式
     * @param date
     * @return
     * @throws Exception
     */
    public static Timestamp getEndTimeStamp(String date) throws ParseException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }else if(date.length()<=10)
        {
            date+=" 23:59:59";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.parse(date);
        return Timestamp.valueOf(date);
    }

    public static void main(String[] args) throws Exception {
        getStartTimeStamp("2017-10-10");
         System.out.println(DateUtil.date2String(getBeforeAfterDate(DateUtil.date2String(new Date()), 90)));
    }

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String HH_MM = "HH:mm";
        String YYYY = "yyyy";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYMMDDHHMMSS = "yyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String MM = "MM";
    }

    /**
     * 页面传入的日期格式字符串转换成timestamp
     *
     * @param date yyyy-MM-dd格式的date字符串
     * @return 转换后的Timestamp
     * @author jw
     */
    public static Timestamp convertDateStringToTimestamp(String date, String partern) throws GeneralException {
        if(StringUtils.isEmpty(date)){
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(partern);
        SimpleDateFormat sfFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return Timestamp.valueOf(sfFormat.format(sf.parse(date)));
        }catch(ParseException e){
            throw new GeneralException("GENERAL_O01");
        }
    }

    /**
     * 将数据库中timestamp格式的,末尾带.0的数据格式化成yyyy-MM-dd格式的字符串
     *
     * @param timestamp timestame转换成的字符换 末尾存在.0
     * @return yyyy-MM-dd格式的字符串
     * @author jw
     */
    public static String convertTimestampToString(Object timestamp, String partern) throws GeneralException {
        if(null == timestamp || StringUtils.isBlank(timestamp.toString())){
            return null;
        }

        String value = timestamp.toString();
        SimpleDateFormat sf = new SimpleDateFormat(partern);

        try {
            String val = value.substring(0, value.indexOf("."));
            return sf.format(sf.parse(val));
        } catch (ParseException e) {
            throw new GeneralException("GENERAL_001");
        }
    }

}
