package com.jw.base;


import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.*;

/**
 *
 * Created by lye on 2017/11/18.
 * 公用的出参转换类
 * 作用：1、自动去除时间格式后缀“.0”；2、将long类型转换为String类型；
 */
@Component
public class BaseFormateter implements ValueFormatter{
    @Override
    public Object format(String key, Object value) {
        if (value instanceof Timestamp) {
            // 自动去除时间格式后缀“.0”
            String val = value.toString().substring(0, value.toString().indexOf("."));
            return val;
        }else if (value instanceof Long) {
            //解决出参long精度失真问题，转换为String
            String val = String.valueOf(value);

            return val;
        }else if (value instanceof BigInteger) {
            //解决出参BigInteger精度失真问题，转换为String
            String val = String.valueOf(value);
            return val;
        }
        else{
            return value;
        }
    }

}
