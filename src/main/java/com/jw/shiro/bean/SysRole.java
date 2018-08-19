package com.jw.shiro.bean;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public class SysRole extends TSysRole{
    private List<TSysPermission> permissions;

    public List<TSysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TSysPermission> permissions) {
        this.permissions = permissions;
    }

    private List<UserInfo> userInfos;// 一个角色对应多个用户

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
