package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

import java.util.List;

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

    private List<TTeacherClassRel> classIdArray;

    private String rlId;

    private String rlType;

    public List<TTeacherClassRel> getClassIdArray() {
        return classIdArray;
    }

    public void setClassIdArray(List<TTeacherClassRel> classIdArray) {
        this.classIdArray = classIdArray;
    }

    public String getRlId() {
        return rlId;
    }

    public void setRlId(String rlId) {
        this.rlId = rlId;
    }

    public String getRlType() {
        return rlType;
    }

    public void setRlType(String rlType) {
        this.rlType = rlType;
    }
}
