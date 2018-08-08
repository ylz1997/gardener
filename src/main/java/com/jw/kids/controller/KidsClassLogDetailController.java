package com.jw.kids.controller;

import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TClassLogDetail;
import com.jw.kids.bean.TClassLogDetailVO;
import com.jw.kids.service.KidsClassLogDetailSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author jw
 * @desc
 */
@RestController
@RequestMapping("/KidsLogDetail")
public class KidsClassLogDetailController {
    @Autowired
    private KidsClassLogDetailSV kidsClassLogDetailSV;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(TClassLogDetail tClassLogDetail, Integer start, Integer length, Integer draw) throws GeneralException {
        HashMap result = kidsClassLogDetailSV.listLogDetail(tClassLogDetail, start, length);

        Integer total = kidsClassLogDetailSV.total(tClassLogDetail);
        result.put("draw", draw);
        result.put("recordTotal",total);
        result.put("recordsFiltered",total);
        return JsonUtil.convertObject2Json(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody TClassLogDetailVO tClassLogDetail) throws GeneralException {
        TClassLogDetail logDetail = kidsClassLogDetailSV.add(tClassLogDetail);
        HashMap result = new HashMap();
        result.put("tClassLogDetail", logDetail);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }
}
