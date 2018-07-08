package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

/**
 * @author jw
 * @desc
 */
public class TTeacherVO extends TTeacher{
    private String strCrtTime;

    public String getStrCrtTime() {
        return strCrtTime;
    }

    public void setStrCrtTime(String strCrtTime) throws GeneralException {
        this.strCrtTime = strCrtTime;
        setCrtTime(DateUtil.convertDateStringToTimestamp(strCrtTime, "yyyy-MM-dd HH:mm:ss"));
    }
}
