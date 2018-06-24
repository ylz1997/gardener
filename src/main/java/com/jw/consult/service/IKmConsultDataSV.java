package com.jw.consult.service;


import com.jw.base.GeneralException;
import com.jw.consult.bean.ConsultDataVO;
import com.jw.consult.bean.ConsultSearchVO;
import com.jw.consult.bean.TKmConsultData;

import java.util.List;
import java.util.Map;

/**
 * 咨询表记录业务层接口
 *
 * @author ylz1997  2018-3-2 14:32:28
 */
public interface IKmConsultDataSV {

    /**
     * 新增或更新咨询表记录
     *
     * @param data
     */
    Long addConsultData(Map<String, Object> data) throws GeneralException;

    /**
     * 批量更新咨询表记录
     *
     * @param data
     */
    void batchUpdateConsultData(Map<String, Object> data) throws GeneralException;

    /**
     * 咨询表记录删除
     *
     * @param recIds 记录id
     * @param regnId 地域id
     */
    void deleteConsultData(List<String> recIds, String regnId);

    /**
     * 查询咨询表信息（包含咨询表、记录、字段）
     *
     * @param searchVO vo对象
     * @return ConsultDataBO 咨询表对象实体
     */
    ConsultDataVO getConsultApply(ConsultSearchVO searchVO) throws GeneralException;
/*
    int buildQueryResult(ConsultSearchVO searchVO) throws GeneralException;*/
    /**
     * 查询咨询表记录详细信息
     *
     * @param recId
     * @param regnId
     * @return
     */
    List<TKmConsultData> getConsultData(Long recId, String regnId);

    /**
     * 获取记录id
     *
     * @param map
     * @return
     */
    List<Long> getRecIds(Map<String, Object> map);

    /**
     * 校验数据的正确性
     *
     * @param consultData
     * @param tmpltId
     * @return
     */
    String checkConsultData(Map<String, String> consultData, Long tmpltId, boolean batchFlag);

    /**
     * 校验记录是否存在
     *
     * @param recIds
     * @param regnId
     * @return
     */
    List<Long> checkDelete(String recIds, String regnId);

}
