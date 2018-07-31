package com.jw.kids.controller;

import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TKids;
import com.jw.kids.bean.TKidsLog;
import com.jw.kids.controller.aop.KidsLog;
import com.jw.kids.service.KidsLogSV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author jw
 * @desc
 */
@Controller
public class KidsLogController {
    @Autowired
    private KidsLogSV logSV;

    public void add(String operType, TKids kids) throws GeneralException {
        TKidsLog kidsLog = new TKidsLog();
        BeanUtils.copyProperties(kids, kidsLog);
        kidsLog.setOperType(operType);
        logSV.add(kidsLog);
    }

}
