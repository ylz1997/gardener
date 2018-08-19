package com.jw.shiro.service;

import com.jw.shiro.bean.UserInfo;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jw
 * @desc
 */
public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username) throws InvocationTargetException, IllegalAccessException;
}
