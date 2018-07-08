package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.bean.TClassPackageVO;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public interface KidsClassPackageSV {
    /**
     * 新增
     * @param tClassPackage
     * @return
     * @throws GeneralException
     */
    TClassPackage addClassPackage(TClassPackage tClassPackage) throws GeneralException;

    /**
     * 修改
     * @param tClassPackageVO
     * @return
     * @throws GeneralException
     */
    TClassPackage editClassPackage(TClassPackageVO tClassPackageVO) throws GeneralException;

    /**
     * 删除
     * @param tId
     * @return
     * @throws GeneralException
     */
    TClassPackage deleteClassPackage(String tId) throws GeneralException;

    /**
     * 查询单个
     * @param tId
     * @return
     * @throws GeneralException
     */
    TClassPackage getClassPackageById(Long tId) throws GeneralException;

    /**
     * 查询列表
     * @param tClassPackageVO
     * @return
     * @throws GeneralException
     */
    List<TClassPackage> listClassPackage(TClassPackage tClassPackageVO, Integer start, Integer length) throws GeneralException;

    /**
     * 查询单个
     * @return
     * @param
     * @throws GeneralException
     */
    Integer totalClassPackage(TClassPackage TClassPackage) throws GeneralException;
}
