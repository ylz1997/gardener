package com.jw.kids.controller;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TClassLog;
import com.jw.kids.bean.TClassLogVO;
import com.jw.kids.service.KidsClassLogSV;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@RestController
@RequestMapping("/kidsLog")
public class KidsClassLogController {
    @Autowired
    KidsClassLogSV kidsClassLogSV;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("kidsLog:add")
    public String add(@RequestBody TClassLogVO tClassLog) throws GeneralException {
        tClassLog.setCrtTime(DateUtil.getCurrontTime());
        tClassLog.setModfTime(DateUtil.getCurrontTime());
        tClassLog.setOperator(0001L);
        TClassLog tClassLogResult = kidsClassLogSV.add(tClassLog);
        HashMap result = new HashMap();

        result.put("tClassLog", tClassLogResult);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String list(Long logId) throws GeneralException, InvocationTargetException, IllegalAccessException {
        HashMap result = new HashMap();
        TClassLogVO tClassLog = kidsClassLogSV.get(logId);
        result.put("result",true);
        result.put("tClassLog",tClassLog);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(TClassLog tClassLog, Integer start, Integer length, Integer draw) throws GeneralException {
        List<Map> list = kidsClassLogSV.list(tClassLog, start, length);
        Integer total = kidsClassLogSV.total(tClassLog);
        HashMap result = new HashMap();

        result.put("data",list);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }
}
