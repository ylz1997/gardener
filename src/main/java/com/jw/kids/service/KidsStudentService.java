package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TKids;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public interface KidsStudentService {
    /**
     * 新增
     * @param tKids
     * @return
     * @throws GeneralException
     */
    TKids addKids(TKids tKids) throws GeneralException;

    /**
     * 修改
     * @param tKids
     * @return
     * @throws GeneralException
     */
    TKids editKids(TKids tKids) throws GeneralException;

    /**
     * 删除
     * @param tKids
     * @return
     * @throws GeneralException
     */
    TKids deleteKids(TKids tKids) throws GeneralException;

    /**
     * 查询单个
     * @param kId
     * @return
     * @throws GeneralException
     */
    TKids getKidsById(Long kId) throws GeneralException;

    /**
     * 查询单个
     * @param tKids
     * @return
     * @throws GeneralException
     */
    List<TKids> listKids(TKids tKids,Integer start, Integer length) throws GeneralException;

    /**
     * 查询单个
     * @return
     * @param
     * @throws GeneralException
     */
    Integer totalKids() throws GeneralException;
}
