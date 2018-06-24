package com.jw.base;

/**
 * @author jw
 * @desc
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public abstract class ExceptionEnv {
    static Environment _sharedEnv = null;

    public ExceptionEnv() {
    }

    public static boolean has(String key) {
        Assert.notNull(_sharedEnv);
        return _sharedEnv.containsProperty(key);
    }

    public static String getString(String key) {
        Assert.notNull(_sharedEnv);
        return _sharedEnv.getProperty(key);
    }

    public static String getString(String key, String defval) {
        Assert.notNull(_sharedEnv);
        return _sharedEnv.getProperty(key, defval);
    }

    public static int getInt(String key, int defval) {
        return Integer.parseInt(getString(key, String.valueOf(defval)).trim());
    }

    public static String[] getStringArray(String key) {
        return getStringArray(key, "\\s*[,;]\\s*");
    }

    public static String[] getStringArray(String key, String regexp) {
        return getString(key, "").split(regexp);
    }

    public static int[] getIntArray(String key) {
        return getIntArray(key, "\\s*[,;]\\s*");
    }

    public static int[] getIntArray(String key, String regexp) {
        String[] items = getStringArray(key, regexp);
        int index = 0;
        int[] result = new int[items.length];
        String[] var5 = items;
        int var6 = items.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String item = var5[var7];
            result[index++] = Integer.parseInt(item.trim());
        }

        return result;
    }

    public static Class<?> getClass(String key) throws ClassNotFoundException {
        return Class.forName(getString(key).trim());
    }

    public static Class[] getClassArray(String key) throws ClassNotFoundException {
        return getClassArray(key, "\\s*[,;]\\s*");
    }

    public static Class[] getClassArray(String key, String regexp) throws ClassNotFoundException {
        String[] items = getStringArray(key, regexp);
        int index = 0;
        Class[] result = new Class[items.length];
        String[] var5 = items;
        int var6 = items.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String item = var5[var7];
            result[index++] = Class.forName(item.trim());
        }

        return result;
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key, "false"));
    }

    public static void setPropertySource(PropertySource<?> propertySource) {
        if (_sharedEnv instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment _env = (ConfigurableEnvironment)_sharedEnv;
            if (!_env.getPropertySources().contains(propertySource.getName())) {
                _env.getPropertySources().addLast(propertySource);
            } else {
                _env.getPropertySources().replace(propertySource.getName(), propertySource);
            }
        }

    }

    public static boolean isLoaded() {
        return _sharedEnv != null;
    }

    public static String getErrorMsgByCode(String key) {
        Properties pps = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if(loader == null) {
                loader = ExceptionEnv.class.getClassLoader();
            }
            InputStream inputFile = loader.getResourceAsStream("errors.properties");
            InputStream in = new BufferedInputStream(inputFile);
            pps.load(new InputStreamReader(in, "utf-8"));
            String value = pps.getProperty(key);
            return value;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
