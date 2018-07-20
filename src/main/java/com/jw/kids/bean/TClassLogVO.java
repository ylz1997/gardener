package com.jw.kids.bean;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public class TClassLogVO extends TClassLog {
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
