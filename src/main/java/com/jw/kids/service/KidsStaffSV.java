package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TTeacher;
import com.jw.kids.bean.TTeacherVO;

import java.util.List;
import java.util.Map;

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
    TTeacher addStaff(TTeacherVO tTeacher) throws GeneralException;

    /**
     * 修改
     * @param tTeacher
     * @return
     * @throws GeneralException
     */
    TTeacherVO editStaff(TTeacherVO tTeacher) throws GeneralException;

    /**
     * 删除
     * @param tId
     * @return
     * @throws GeneralException
     */
    TTeacher deleteStaff(String tId) throws GeneralException;

    /**
     * 查询单个
     * @param tId
     * @return
     * @throws GeneralException
     */
    TTeacherVO getStaffById(Long tId) throws GeneralException;

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

    List<Map> listByClassId(Long classId);
}
