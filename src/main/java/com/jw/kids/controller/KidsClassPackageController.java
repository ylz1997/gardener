package com.jw.kids.controller;

import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.dao.TClassPackageDAO;
import com.jw.kids.service.KidsClassPackageSV;
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
@RequestMapping("/classPackage")
public class KidsClassPackageController {
    @Autowired
    KidsClassPackageSV kidsClassPackageSV;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addKids(@Valid @RequestBody TClassPackage tClassPackage) throws GeneralException {
        tClassPackage.setCrtTime(new Date());
        tClassPackage.setModfTime(new Date());
        tClassPackage.setOperator(0001L);
        TClassPackage tClassPackageResult = kidsClassPackageSV.addClassPackage(tClassPackage);
        HashMap result = new HashMap();

        result.put("tClassPackage", tClassPackageResult);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String editKids(@Valid @RequestBody TClassPackage TClassPackage) throws GeneralException {
        TClassPackage.setModfTime(new Date());
        TClassPackage.setOperator(0001L);
        TClassPackage tClassPackageResult = kidsClassPackageSV.editClassPackage(TClassPackage);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClassPackage",tClassPackageResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteKids(String classPackageId) throws GeneralException {
        TClassPackage tClassPackageResult = kidsClassPackageSV.deleteClassPackage(classPackageId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClassPackage",tClassPackageResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public String getKids(Long classPackageId) throws GeneralException {
        TClassPackage TClassPackage = kidsClassPackageSV.getClassPackageById(classPackageId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tClassPackage",TClassPackage);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listKids(TClassPackage TClassPackage, Integer start, Integer length, Integer draw) throws GeneralException {
        List<TClassPackage> listClassPackage = kidsClassPackageSV.listClassPackage(TClassPackage, start, length);
        Integer total = kidsClassPackageSV.totalClassPackage(TClassPackage);
        HashMap result = new HashMap();

        result.put("data",listClassPackage);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }
}
