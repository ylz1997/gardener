package com.jw.kids.controller;

import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TKids;
import com.jw.kids.bean.TKidsExample;
import com.jw.kids.service.KidsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

/**
 * @author jw
 * @desc
 */
@RestController
@RequestMapping("/Kids")
public class KidsStudentController {

    @Autowired
    private KidsStudentService kidsStudentService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addKids(@Valid @RequestBody TKids tKids) throws GeneralException {
        tKids.setCrtTime(new Date());
        tKids.setModfTime(new Date());
        tKids.setOperator(0001L);
        kidsStudentService.addStudent(tKids);
        HashMap result = new HashMap();

        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }
}
