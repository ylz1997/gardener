package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TKidsLog;
import com.jw.kids.bean.TTeacher;

/**
 * @author jw
 * @desc
 */
public interface KidsLogSV {
    /**
     * 新增
     * @param
     * @return
     * @throws GeneralException
     */
    boolean add(TKidsLog log) throws GeneralException;

}
