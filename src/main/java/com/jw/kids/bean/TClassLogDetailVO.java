package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

/**
 * @author jw
 * @desc
 */
public class TClassLogDetailVO extends TClassLogDetail {
    String strClassTime;

    public String getStrClassTime() {
        return strClassTime;
    }

    public void setStrClassTime(String strClassTime) throws GeneralException {
        this.strClassTime = strClassTime;
        super.setClassTime(DateUtil.convertDateStringToTimestamp(strClassTime,"yyyy-MM-dd HH:mm:ss"));
    }
}
