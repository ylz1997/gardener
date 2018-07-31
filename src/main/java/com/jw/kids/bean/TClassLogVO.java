package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public class TClassLogVO extends TClassLog {
    private String strCrtTime;

    public String getStrCrtTime() {
        return strCrtTime;
    }

    public void setStrCrtTime(String strCrtTime) throws GeneralException {
        this.strCrtTime = strCrtTime;
        setCrtTime(DateUtil.convertDateStringToTimestamp(strCrtTime,"yyyy-MM-dd HH:mm:ss"));
    }

    private String strClassTime;

    public String getStrClassTime() {
        return strClassTime;
    }

    public void setStrClassTime(String strClassTime) throws GeneralException {
        this.strClassTime = strClassTime;
        setClassTime(DateUtil.convertDateStringToTimestamp(strClassTime,"yyyy-MM-dd HH:mm:ss"));
    }

    private List<TClassLogDetail> teacherList;
    private List<TClassLogDetail> kidsList;

    public List<TClassLogDetail> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TClassLogDetail> teacherList) {
        this.teacherList = teacherList;
    }

    public List<TClassLogDetail> getKidsList() {
        return kidsList;
    }

    public void setKidsList(List<TClassLogDetail> kidsList) {
        this.kidsList = kidsList;
    }

    @Override
    public String toString() {
        return "TClassLogVO{" +
                "strCrtTime='" + strCrtTime + '\'' +
                ", strClassTime='" + strClassTime + '\'' +
                ", teacherList=" + teacherList +
                ", kidsList=" + kidsList +
                '}' + "::super:"+ super.toString();
    }
}
