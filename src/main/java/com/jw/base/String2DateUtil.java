package com.jw.base;

import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将字符串日期与long类型之间转换
 *
 */
public class String2DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger LOGGER = LoggerFactory.getLogger(String2DateUtil.class);

    /**
     * 日期类型字符串转为long类型数值
     *
     * @param dateString 日期类型字符串
     * @return 该日期对应的当前时间毫秒值
     */
    public static Long dateString2Long(String dateString) throws GeneralException{
        if(StringUtil.isEmpty(dateString)){
            return null;
        }

        try{
            return sdf.parse(dateString).getTime();
        }catch(Exception e){
            LOGGER.error("日期字符串转long类型出错", e);
            throw new GeneralException("STRING2DATE2LONG001");
        }

    }

    /**
     * long类型字符串转日期时间类型
     *
     * @param longString
     * @return
     * @throws GeneralException
     */
    public static String LongString2Date(String longString) throws GeneralException{
        if(StringUtil.isEmpty(longString)){
            return null;
        }

        try{
            return sdf.format(new Date(Long.parseLong(longString)));
        }catch (Exception e){
            LOGGER.error("字符串转日期出错", e);
            throw new GeneralException("STRING2DATE2LONG002");
        }
    }

}
