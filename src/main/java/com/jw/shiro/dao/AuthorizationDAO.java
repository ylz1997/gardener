package com.jw.shiro.dao;

import com.jw.shiro.bean.TSysPermission;
import com.jw.shiro.bean.TSysRole;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public interface AuthorizationDAO {
    /**
     * 获取用户名下所有角色
     * @param userName 用户名
     * @return
     */
    List<TSysRole> selectRoleByUserName(String userName);

    /**
     * 获取角色下所有权限
     * @param roleId 角色Id
     * @return
     */
    List<TSysPermission> selectPermissionByRoleId(Long roleId);
}
