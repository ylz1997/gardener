package com.jw.kids.service;

import com.jw.base.GeneralException;
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
    TClassVO addClass(TClassVO TClassVO) throws GeneralException;

    /**
     * 修改
     * @param TClassVO
     * @return
     * @throws GeneralException
     */
    TClassVO editClass(TClassVO TClassVO) throws GeneralException;

    /**
     * 删除
     * @param tId
     * @return
     * @throws GeneralException
     */
    TClassVO deleteClass(String tId) throws GeneralException;

    /**
     * 查询单个
     * @param tId
     * @return
     * @throws GeneralException
     */
    TClassVO getClassById(Long tId) throws GeneralException;

    /**
     * 查询列表
     * @param tClassVO
     * @return
     * @throws GeneralException
     */
    List<TClassVO> listClass(TClassVO tClassVO, Integer start, Integer length) throws GeneralException;

    /**
     * 查询单个
     * @return
     * @param
     * @throws GeneralException
     */
    Integer totalClass(TClassVO tClassVO) throws GeneralException;
}
