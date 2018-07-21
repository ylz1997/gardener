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

    private List<TTeacher> teacherList;
    private List<TKids> kidsList;

    public List<TTeacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TTeacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<TKids> getKidsList() {
        return kidsList;
    }

    public void setKidsList(List<TKids> kidsList) {
        this.kidsList = kidsList;
    }
}
