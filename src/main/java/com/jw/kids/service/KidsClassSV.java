package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassVO;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public interface KidsClassSV {
    /**
     * 新增
     * @param TClassVO
     * @return
     * @throws GeneralException
     */
    TClass addClass(TClassVO TClassVO) throws GeneralException;

    /**
     * 修改
     * @param TClassVO
     * @return
     * @throws GeneralException
     */
    TClass editClass(TClass TClassVO) throws GeneralException;

    /**
     * 删除
     * @param tId
     * @return
     * @throws GeneralException
     */
    TClass deleteClass(String tId) throws GeneralException;

    /**
     * 查询单个
     * @param tId
     * @return
     * @throws GeneralException
     */
    TClass getClassById(Long tId) throws GeneralException;

    /**
     * 查询列表
     * @param tClass
     * @return
     * @throws GeneralException
     */
    List<TClass> listClass(TClass tClass, Integer start, Integer length) throws GeneralException;

    /**
     * 查询单个
     * @return
     * @param
     * @throws GeneralException
     */
    Integer totalClass(TClass tClass) throws GeneralException;
}
