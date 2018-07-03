package com.jw.kids.controller;

import com.jw.base.JsonUtil;
import com.jw.kids.bean.TKidsSysPara;
import com.jw.kids.service.KidsStudentService;
import com.jw.kids.service.KidsSysParamService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@RequestMapping("/SysParam")
public class KidsSysParaComponent {
    @Autowired
    KidsSysParamService kidsSysParamService;

    public Object get(String code){
        return this.param.get(code);
    }

    private Map<String,Map> param = new HashMap<>();

    public void init(){
        List<TKidsSysPara> vl = kidsSysParamService.findKey(null, null);
        for(TKidsSysPara v:vl){
            if(param.isEmpty()){
                Map<String, String> map = new HashMap<>();
                map.put(v.getParamValue(),v.getParamNm());
                param.put(v.getParamKey(), map);
            }
            else{
                Map map = param.get(v.getParamKey());
                map.put(v.getParamValue(),v.getParamNm());
                param.put(v.getParamKey(), map);
            }
        }
    }
}
