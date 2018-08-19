package com.jw.shiro.bean;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public class UserInfo extends TUserInfo{
    private List<SysRole> roleList;// 一个用户具有多个角色

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.getUserName()+this.getSalt();
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}
