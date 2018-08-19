package com.jw.shiro.bean;

import java.sql.Timestamp;

public class TSysPermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.permission_id
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Long permissionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.name
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.resource_type
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Byte resourceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.url
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.permission
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private String permission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.parent_Id
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.parent_Ids
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private String parentIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.available
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Boolean available;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.crt_time
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Timestamp crtTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.modf_time
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Timestamp modfTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.operator
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private Long operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_permission.rmk
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    private String rmk;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_permission
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public TSysPermission(Long permissionId, String name, Byte resourceType, String url, String permission, Long parentId, String parentIds, Boolean available, Timestamp crtTime, Timestamp modfTime, Long operator, String rmk) {
        this.permissionId = permissionId;
        this.name = name;
        this.resourceType = resourceType;
        this.url = url;
        this.permission = permission;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.available = available;
        this.crtTime = crtTime;
        this.modfTime = modfTime;
        this.operator = operator;
        this.rmk = rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_permission
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public TSysPermission() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.permission_id
     *
     * @return the value of t_sys_permission.permission_id
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.permission_id
     *
     * @param permissionId the value for t_sys_permission.permission_id
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.name
     *
     * @return the value of t_sys_permission.name
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.name
     *
     * @param name the value for t_sys_permission.name
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.resource_type
     *
     * @return the value of t_sys_permission.resource_type
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Byte getResourceType() {
        return resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.resource_type
     *
     * @param resourceType the value for t_sys_permission.resource_type
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.url
     *
     * @return the value of t_sys_permission.url
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.url
     *
     * @param url the value for t_sys_permission.url
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.permission
     *
     * @return the value of t_sys_permission.permission
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.permission
     *
     * @param permission the value for t_sys_permission.permission
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.parent_Id
     *
     * @return the value of t_sys_permission.parent_Id
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.parent_Id
     *
     * @param parentId the value for t_sys_permission.parent_Id
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.parent_Ids
     *
     * @return the value of t_sys_permission.parent_Ids
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.parent_Ids
     *
     * @param parentIds the value for t_sys_permission.parent_Ids
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.available
     *
     * @return the value of t_sys_permission.available
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.available
     *
     * @param available the value for t_sys_permission.available
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.crt_time
     *
     * @return the value of t_sys_permission.crt_time
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Timestamp getCrtTime() {
        return crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.crt_time
     *
     * @param crtTime the value for t_sys_permission.crt_time
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.modf_time
     *
     * @return the value of t_sys_permission.modf_time
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Timestamp getModfTime() {
        return modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.modf_time
     *
     * @param modfTime the value for t_sys_permission.modf_time
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setModfTime(Timestamp modfTime) {
        this.modfTime = modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.operator
     *
     * @return the value of t_sys_permission.operator
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.operator
     *
     * @param operator the value for t_sys_permission.operator
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_permission.rmk
     *
     * @return the value of t_sys_permission.rmk
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_permission.rmk
     *
     * @param rmk the value for t_sys_permission.rmk
     *
     * @mbggenerated Sun Aug 19 14:01:10 CST 2018
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
    }
}