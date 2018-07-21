package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassLog;
import com.jw.kids.bean.TClassLog;
import com.jw.kids.bean.TClassLogVO;

import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
public interface KidsClassLogSV {
    /**
     * 新增
     * @param tClassLog
     * @return
     * @throws GeneralException
     */
    TClassLogVO add(TClassLogVO tClassLog) throws GeneralException;

    /**
     * 修改
     * @param tClassLog
     * @return
     * @throws GeneralException
     */
    TClassLogVO edit(TClassLogVO tClassLog) throws GeneralException;

    /**
     * 删除
     * @param id
     * @return
     * @throws GeneralException
     */
    TClassLog delete(String id) throws GeneralException;

    /**
     * 查询单个
     * @param id
     * @return
     * @throws GeneralException
     */
    TClassLog get(Long id) throws GeneralException;

    /**
     * 查询列表
     * @param tClassLog
     * @return
     * @throws GeneralException
     */
    List<Map> list(TClassLog tClassLog, Integer start, Integer length) throws GeneralException;

    /**
     * 查询单个
     * @return
     * @param
     * @throws GeneralException
     */
    Integer total(TClassLog tClassLog) throws GeneralException;
}
