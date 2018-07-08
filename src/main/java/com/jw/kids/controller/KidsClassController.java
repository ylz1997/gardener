package com.jw.kids.controller;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassVO;
import com.jw.kids.service.KidsClassSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
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
    public String add(@Valid @RequestBody TClassVO tClassVO) throws GeneralException {
        tClassVO.setCrtTime(DateUtil.getCurrontTime());
        tClassVO.setModfTime(DateUtil.getCurrontTime());
        tClassVO.setOperator(0001L);
        TClass tClassResult = kidsClassSV.addClass(tClassVO);
        HashMap result = new HashMap();

        result.put("tClass", tClassResult);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(@Valid @RequestBody TClassVO TClassVO) throws GeneralException {
        TClassVO.setModfTime(DateUtil.getCurrontTime());
        TClassVO.setOperator(0001L);
        TClass tClassResult = kidsClassSV.editClass(TClassVO);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClass",tClassResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(String classId) throws GeneralException {
        TClass tClassResult = kidsClassSV.deleteClass(classId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClass",tClassResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public String get(Long classId) throws GeneralException {
        TClassVO tClassVO = kidsClassSV.getClassById(classId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClassVO",tClassVO);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(TClassVO tClassVO, Integer start, Integer length, Integer draw) throws GeneralException {
        List<TClassVO> listClass = kidsClassSV.listClass(tClassVO, start, length);
        Integer total = kidsClassSV.totalClass(tClassVO);
        HashMap result = new HashMap();

        result.put("data",listClass);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }
}
