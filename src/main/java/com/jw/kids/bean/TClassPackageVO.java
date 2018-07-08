package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

/**
 * @author jw
 * @desc
 */
public class TClassPackageVO extends TClassPackage{
    private String strStartTime;

    private String strEndTime;

    private String strCrtTime;

    public String getStrStartTime() {
        return strStartTime;
    }

    public void setStrStartTime(String strStartTime) throws GeneralException {
        this.strStartTime = strStartTime;
        setStartTime(DateUtil.convertDateStringToTimestamp(strStartTime, "yyyy-MM-dd HH:mm:ss"));
    }

    public String getStrEndTime() {
        return strEndTime;
    }

    public void setStrEndTime(String strEndTime) throws GeneralException {
        this.strEndTime = strEndTime;
        setEndTime(DateUtil.convertDateStringToTimestamp(strEndTime, "yyyy-MM-dd HH:mm:ss"));
    }

    public String getStrCrtTime() {
        return strCrtTime;
    }

    public void setStrCrtTime(String strCrtTime) throws GeneralException {
        this.strCrtTime = strCrtTime;
        setCrtTime(DateUtil.convertDateStringToTimestamp(strCrtTime, "yyyy-MM-dd HH:mm:ss"));
    }
}
