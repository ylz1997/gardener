package com.jw.consult.service;


import com.jw.base.GeneralException;
import com.jw.consult.bean.TKmConsultApply;
import com.jw.consult.bean.TKmConsultApplyVO;

import java.util.List;
import java.util.Map;

/**
 * 咨询表SV接口
 *
 * @author ylz1997  2018-02-28 11:10:42
 */
public interface IKmConsultSV {

    /**
     * 查询咨询表列表
     *
     * @param tKmConsultApplyVO
     * @param page
     * @param limit
     * @return
     * @ 
     */
    Map<String, Object> listConsultApply(TKmConsultApplyVO tKmConsultApplyVO, Integer page, Integer limit) throws GeneralException;

    /**
     * 新增咨询表
     *
     * @param tKmConsultApply
     * @ 
     */
    Long addConsult(TKmConsultApply tKmConsultApply) throws GeneralException;

    /**
     * 修改咨询表
     *
     * @param tKmConsultApply
     * @ 
     */
    void updateConsult(TKmConsultApply tKmConsultApply) throws GeneralException;

    /**
     * 删除咨询表
     *
     * @param cnslId
     * @param regnId
     * @ 
     */
    void deleteConsult(Long cnslId, String regnId) throws GeneralException;

    /**
     * 获取咨询表详情
     *
     * @param cnslId
     * @return
     * @ 
     */
    TKmConsultApply getConsultContent(Long cnslId) throws GeneralException;

    /**
     * 校验咨询表名称
     *
     * @param cnslId
     * @param cnslNm
     * @param regnId
     * @return
     * @ 
     */
    boolean checkConsultNm(Long cnslId, String cnslNm, String regnId) throws GeneralException;

    /**
     * 查询咨询表id的集合
     *
     * @param condition
     * @return
     */
    List<Long> getConsultIds(Map<String, Object> condition);

}
