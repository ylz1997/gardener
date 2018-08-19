package com.jw.shiro.service.impl;

import com.jw.shiro.bean.TUserInfo;
import com.jw.shiro.bean.TUserInfoExample;
import com.jw.shiro.bean.UserInfo;
import com.jw.shiro.dao.TUserInfoDAO;
import com.jw.shiro.service.UserInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * @author jw
 * @desc
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private TUserInfoDAO userInfoDAO;
    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Override
    public UserInfo findByUsername(String username) throws InvocationTargetException, IllegalAccessException {
        logger.info("UserInfoServiceImpl.findByUsername() "+ username);
        TUserInfoExample example = new TUserInfoExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<TUserInfo> userInfoList = userInfoDAO.selectByExample(example);
        if (userInfoList.size() == 1){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userInfoList.get(0), userInfo);
            return userInfo;
        }
        else {
            return null;
        }
    }
}
