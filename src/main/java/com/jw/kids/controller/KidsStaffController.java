package com.jw.kids.controller;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TTeacher;
import com.jw.kids.bean.TTeacherClassRel;
import com.jw.kids.bean.TTeacherVO;
import com.jw.kids.service.KidsStaffSV;
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
@RequestMapping("/Staff")
public class KidsStaffController {
    @Autowired
    private KidsStaffSV kidsStaffSV;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid @RequestBody TTeacherVO tTeacher) throws GeneralException {
        tTeacher.setCrtTime(DateUtil.getCurrontTime());
        tTeacher.setModfTime(DateUtil.getCurrontTime());
        tTeacher.setOperator(0001L);
        TTeacher teacherResult = kidsStaffSV.addStaff(tTeacher);
        HashMap result = new HashMap();

        result.put("tStaff",teacherResult);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(@Valid @RequestBody TTeacherVO tTeacher) throws GeneralException {
        tTeacher.setModfTime(DateUtil.getCurrontTime());
        tTeacher.setOperator(0001L);
        TTeacher tTeacherResult = kidsStaffSV.editStaff(tTeacher);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tStaff",tTeacherResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(String tId) throws GeneralException {
        TTeacher tTeacherResult = kidsStaffSV.deleteStaff(tId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tStaff",tTeacherResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public String get(Long tId) throws GeneralException {
        TTeacher tTeacher = kidsStaffSV.getStaffById(tId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tStaff",tTeacher);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(TTeacher tTeacher, Integer start, Integer length, Integer draw) throws GeneralException {
        List<TTeacher> listTeacher = kidsStaffSV.listStaff(tTeacher, start, length);
        Integer total = kidsStaffSV.totalStaff(tTeacher);
        HashMap result = new HashMap();

        result.put("data",listTeacher);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }
}
