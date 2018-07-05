package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TTeacher;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public interface KidsStaffSV {
    /**
     * 新增
     * @param tTeacher
     * @return
     * @throws GeneralException
     */
    TTeacher addStaff(TTeacher tTeacher) throws GeneralException;

    /**
     * 修改
     * @param tTeacher
     * @return
     * @throws GeneralException
     */
    TTeacher editStaff(TTeacher tTeacher) throws GeneralException;

    /**
     * 删除
     * @param tTeacher
     * @return
     * @throws GeneralException
     */
    TTeacher deleteStaff(TTeacher tTeacher) throws GeneralException;

    /**
     * 查询单个
     * @param tId
     * @return
     * @throws GeneralException
     */
    TTeacher getStaffById(Long tId) throws GeneralException;

    /**
     * 查询列表
     * @param tTeacher
     * @return
     * @throws GeneralException
     */
    List<TTeacher> listStaff(TTeacher tTeacher, Integer start, Integer length) throws GeneralException;

    /**
     * 查询单个
     * @return
     * @param
     * @throws GeneralException
     */
    Integer totalStaff(TTeacher tTeacher) throws GeneralException;
}
