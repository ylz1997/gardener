package com.jw.consult.bean;

import java.sql.Timestamp;

public class TKmConsultApplyVO extends TKmConsultApply {

    /**
     * 开始时间
     */
    private Timestamp startTime;

    /**
     * 结束时间
     */
    private Timestamp endTime;

    public void setStartTime(Timestamp startTime){
        this.startTime = startTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }
}
