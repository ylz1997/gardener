package com.jw.kids.controller;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TKids;
import com.jw.kids.bean.TKidsExample;
import com.jw.kids.bean.TKidsVO;
import com.jw.kids.controller.aop.KidsLog;
import com.jw.kids.service.KidsStudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/Kids")
public class KidsStudentController {

    @Autowired
    private KidsStudentService kidsStudentService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequiresPermissions("kids:add")
    @KidsLog(operType = "add")
    public String add(@Valid @RequestBody TKidsVO tKids) throws GeneralException {
        tKids.setCrtTime(DateUtil.getCurrontTime());
        tKids.setModfTime(DateUtil.getCurrontTime());
        tKids.setOperator(0001L);
        TKids kidsResult = kidsStudentService.addKids(tKids);
        HashMap result = new HashMap();

        result.put("tKids",kidsResult);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }
    @KidsLog(operType = "edit")
    @RequiresPermissions("kids:edit")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(@Valid @RequestBody TKidsVO tKids) throws GeneralException {
        tKids.setModfTime(DateUtil.getCurrontTime());
        tKids.setOperator(0001L);
        TKids kidsResult = kidsStudentService.editKids(tKids);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tKids",kidsResult);
        return JsonUtil.convertObject2Json(result);
    }

    @KidsLog(operType = "delete")
    @RequiresPermissions("kids:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(String kId) throws GeneralException {
        TKids kidsResult = kidsStudentService.deleteKids(kId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tKids",kidsResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public String get(Long kId) throws GeneralException {
        TKids kidsResult = kidsStudentService.getKidsById(kId);
        HashMap result = new HashMap();

        result.put("result",true);
        result.put("tKids",kidsResult);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(TKids tKids, Integer start, Integer length, Integer draw) throws GeneralException {
        List<TKids> listTkids = kidsStudentService.listKids(tKids, start, length);
        Integer total = kidsStudentService.totalKids(tKids);
        HashMap result = new HashMap();

        result.put("data",listTkids);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }
    @KidsLog(operType = "charge")
    @RequestMapping(value = "/charge",method = RequestMethod.POST)
    @RequiresPermissions("kids:charge")
    public String charge(String kId, String classPackageId) throws GeneralException {

        HashMap result = kidsStudentService.charge(kId, classPackageId);

        result.put("result",true);

        return JsonUtil.convertObject2Json(result);
    }

}
