package com.jw.kids.controller;

import com.jw.base.JsonUtil;
import com.jw.kids.bean.TKidsSysPara;
import com.jw.kids.service.KidsStudentService;
import com.jw.kids.service.KidsSysParamService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc 初始化加载系统参数类
 */
public class KidsSysParaComponent {
    @Autowired
    KidsSysParamService kidsSysParamService;

    public Object get(String code){
        return this.param.get(code);
    }

    private Map<String,List> param = new HashMap<>();

    public void init(){
        List<TKidsSysPara> vl = kidsSysParamService.findKey(null, null);
        for(TKidsSysPara v:vl){
            if(param.isEmpty()){
                Map<String, String> map = new HashMap<>();
                map.put("value", v.getParamValue());
                map.put("name", v.getParamNm());
                List list = new ArrayList<Map>();
                list.add(map);
                param.put(v.getParamKey(), list);
            }
            else{
                if(null == param.get(v.getParamKey())){
                    Map<String, String> map = new HashMap<>();
                    map.put("value", v.getParamValue());
                    map.put("name", v.getParamNm());
                    List list = new ArrayList<Map>();
                    list.add(map);
                    param.put(v.getParamKey(), list);
                }
                else{
                    List list = param.get(v.getParamKey());
                    Map<String, String> map = new HashMap();
                    map.put("value", v.getParamValue());
                    map.put("name", v.getParamNm());
                    list.add(map);
                    param.put(v.getParamKey(), list);
                }

            }
        }
    }
}
