package com.jw.base;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * json转换工具类
 *
 */
public final class JsonUtil {

    private static ObjectMapper objectMapper;
    private static Map convertorMap = new HashMap();
    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private JsonUtil() {
    }

    /**
     * 将object对象转换为json
     *
     * @param object
     * @return
     */
    public static String convertObject2Json(Object object) {
        String method = "convertObject2Json";
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            System.out.print(method+"convert json error."+object.toString());
        }
        return null;
    }

    /**
     * 将json对象转换为Object
     *
     * @param json
     * @param cls
     * @return
     */
    public static Object convertJson2Object(String json, Class cls) {
        String method = "convertJson2Object";
        try {
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            System.out.print(method+"convert json error."+json.toString());
        }
        return null;
    }
}
