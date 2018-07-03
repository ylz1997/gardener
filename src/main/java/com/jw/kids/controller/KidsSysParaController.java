package com.jw.kids.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.jw.base.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author jw
 * @desc
 */
@RestController
@RequestMapping("/SysPara")
public class KidsSysParaController {
    @Autowired
    KidsSysParaComponent kidsSysParaComponent;

    public Object get(String code){
        return kidsSysParaComponent.get(code);
    }

    @RequestMapping(value = "/getParamByCode", method = RequestMethod.GET)
    public String getString(String code){
        Object o = this.get(code);
        HashMap result = new HashMap();

        result.put("param",o);
        result.put("result",true);
        return JsonUtil.convertObject2Json(result);
    }
}
