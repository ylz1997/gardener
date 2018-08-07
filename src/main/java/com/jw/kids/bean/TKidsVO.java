package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

/**
 * @author jw
 * @desc
 */
public class TKidsVO extends TKids{
    private String strCrtTime;


    private String strBirthday;

    public String getStrCrtTime() {
        return strCrtTime;
    }

    public void setStrCrtTime(String strCrtTime) throws GeneralException {
        this.strCrtTime = strCrtTime;
        setCrtTime(DateUtil.convertDateStringToTimestamp(strCrtTime, "yyyy-MM-dd HH:mm:ss"));
    }

    public String getStrBirthday() {
        return strBirthday;
    }

    public void setStrBirthday(String strBirthday) throws GeneralException {
        this.strBirthday = strBirthday;
        setBirthday(DateUtil.convertDateStringToTimestamp(strBirthday, "yyyy-MM-dd HH:mm:ss"));
    }
}
