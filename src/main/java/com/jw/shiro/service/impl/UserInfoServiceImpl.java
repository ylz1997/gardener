package com.jw.shiro.service.impl;

import com.jw.shiro.bean.*;
import com.jw.shiro.dao.AuthorizationDAO;
import com.jw.shiro.dao.TSysPermissionDAO;
import com.jw.shiro.dao.TSysRoleDAO;
import com.jw.shiro.dao.TUserInfoDAO;
import com.jw.shiro.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author jw
 * @desc
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private TUserInfoDAO userInfoDAO;
    @Autowired
    private TSysRoleDAO sysRoleDAO;
    @Autowired
    private TSysPermissionDAO sysPermissionDAO;
    @Autowired
    private AuthorizationDAO authorizationDAO;

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

            //查询角色
            List<TSysRole> tSysRoleList = authorizationDAO.selectRoleByUserName(username);
            List<SysRole> sysRoleList = new ArrayList<>();
            for(TSysRole role: tSysRoleList){
                SysRole sysRole = new SysRole();
                BeanUtils.copyProperties(role, sysRole);
                //查询权限
                List<TSysPermission> listPermission = authorizationDAO.selectPermissionByRoleId(sysRole.getRoleId());
                sysRole.setPermissions(listPermission);
                sysRoleList.add(sysRole);
            }
            userInfo.setRoleList(sysRoleList);
            return userInfo;
        }
        else {
            logger.error("=======用户名不唯一 size = " + userInfoList.size());
            return null;
        }
    }
}
