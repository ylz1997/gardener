package com.jw.kids.controller;

import com.jw.kids.bean.TKidsSysPara;
import com.jw.kids.service.KidsStudentService;
import com.jw.kids.service.KidsSysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@RequestMapping("/sysParam")
public class KidsSysParaController {
    @Autowired
    KidsSysParamService kidsSysParamService;

    private static KidsSysParaController instance = new KidsSysParaController();

    private Map<String,Object> param = new HashMap<>();

    public static KidsSysParaController getInstance(){
        return instance;
    }
    public Object get(String code){
        return this.param.get(code);
    }
    @RequestMapping("/getParamByCode")
    public String getString(String code,String defultValue){
        Object o = this.get(code);
        return o!=null?o.toString():defultValue;
    }
    public void init(){

        List<TKidsSysPara> vl = kidsSysParamService.findKey("removed", "0");
        for(TKidsSysPara v:vl){
            param.put(v.getParamKey(), v.getParamValue());
            //remark.put(v.getCode(), v.getRemark());
            System.err.print("key:"+v.getParamKey()+"----value:"+v.getParamKey());
        }
    }
}
