package com.jw.kids.controller;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassVO;
import com.jw.kids.service.KidsClassSV;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @author jw
 * @desc
 */
@RestController
@RequestMapping("/class")
public class KidsClassController {
    @Autowired
    KidsClassSV kidsClassSV;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequiresPermissions("class:add")
    public String add(@Valid @RequestBody TClassVO tClass) throws GeneralException {
        tClass.setCrtTime(DateUtil.getCurrontTime());
        tClass.setModfTime(DateUtil.getCurrontTime());
        tClass.setOperator(0001L);
        TClass tClassResult = kidsClassSV.addClass(tClass);
        HashMap result = new HashMap();

        result.put("tClass", tClassResult);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @RequiresPermissions("class:edit")
    public String edit(@Valid @RequestBody TClassVO tClass) throws GeneralException {
        tClass.setModfTime(DateUtil.getCurrontTime());
        tClass.setOperator(0001L);
        TClass tClassResult = kidsClassSV.editClass(tClass);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClass",tClassResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @RequiresPermissions("class:delete")
    public String delete(String classId) throws GeneralException {
        TClass tClassResult = kidsClassSV.deleteClass(classId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClass",tClassResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public String get(Long classId) throws GeneralException {
        TClass tClass = kidsClassSV.getClassById(classId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClass",tClass);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(TClass tClass, Integer start, Integer length, Integer draw) throws GeneralException {
        List<TClassVO> listClass = kidsClassSV.listClass(tClass, start, length);
        Integer total = kidsClassSV.totalClass(tClass);
        HashMap result = new HashMap();

        result.put("data",listClass);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }
}
