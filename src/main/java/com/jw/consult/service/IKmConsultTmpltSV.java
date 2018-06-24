package com.jw.consult.service;


import com.jw.base.GeneralException;
import com.jw.consult.bean.TKmConsultKeys;
import com.jw.consult.bean.TKmConsultTmplt;

import java.util.List;

public interface IKmConsultTmpltSV {
    /**
     * 查询模板列表
     *
     * @param tKmConsultTmplt
     * @return
     */
    List<TKmConsultTmplt> getConsultTmpltList(final Integer limit, final Integer page, final TKmConsultTmplt tKmConsultTmplt);

    /**
     * 查询模板列表总数
     *
     * @param tKmConsultTmplt
     * @return
     */
    int getConsultTmpltListCount(TKmConsultTmplt tKmConsultTmplt);


    /**
     * @param tKmConsultTmplt 模板对象
     * @return List 模板列表
     */
    List<TKmConsultTmplt> getConsultTmpltList(TKmConsultTmplt tKmConsultTmplt);

    /**
     * @param tKmConsultTmplt 模板对象 带分页
     * @return List 模板列表
     */
    List<TKmConsultTmplt> getConsultTmpltList(TKmConsultTmplt tKmConsultTmplt, int startRow, int pageSize);

    /**
     * @param tmpltId 模板id
     * @return TKmConsultTmplt 模板对象
     */
    TKmConsultTmplt getConsultTmplt(Long tmpltId);

    /**
     * @param columnId 模板列id
     * @return TKmConsultKeys
     */
    public TKmConsultKeys getConsultTmpltColumn(Long columnId);

    /**
     * @param tmpltId 模板id
     * @return List 模板列定义列表 带分页
     */
    List listTKmConsultKeys(Long tmpltId, int startRow, int pageSize);

    /**
     * @param tmpltId 模板id
     * @return List 模板列定义列表 不带分页
     */
    List listTKmConsultKeys(Long tmpltId);

    /**
     * @param tKmConsultTmplt bean
     * @return 主键
     */
    Long saveConsultTmplt(TKmConsultTmplt tKmConsultTmplt) throws GeneralException;

    /**
     * @param tKmConsultKeys bean
     * @return 主键
     */
    Long saveOrUpdateConsultTmpltColm(TKmConsultKeys tKmConsultKeys) throws GeneralException;

    /**
     * 删除咨询表列
     *
     * @param colmId 列id
     * @return 主键
     */
    Long deleteConsultKeys(String colmId) throws GeneralException;

    /**
     * 修改TKmConsultKeys排序
     *
     * @param example 实例
     * @return 主键
     */
    void modifyArgeSeqno(TKmConsultKeys example) throws GeneralException;


    /**
     * 查询目录下最大的排列序号
     *
     * @param catlId
     * @return argeSeqno
     */
    Short getMaxArgeSeqnoByCaltId(Long catlId);

    /**
     * @description:保存模板信息
     * @param:TKmConsultTmplt
     * @return:boolean
     */
    boolean save(TKmConsultTmplt tKmConsultTmplt) throws GeneralException;

    boolean extendTmpltInfo(TKmConsultTmplt tKmConsultTmplt, String newTmpletId) throws GeneralException;

    boolean updateByPrimaryKeySelective(TKmConsultTmplt tKmConsultTmplt) throws GeneralException;

    void deleteByPrimaryKey(Long tmpltId) throws GeneralException;

    List<TKmConsultKeys> getAllKeys(Long tmpltId);
}