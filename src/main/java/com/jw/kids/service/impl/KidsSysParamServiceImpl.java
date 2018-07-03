package com.jw.kids.service.impl;

import com.github.pagehelper.StringUtil;
import com.jw.kids.bean.TKidsSysParaExample;
import com.jw.kids.dao.TKidsSysParaDAO;
import com.jw.kids.service.KidsStudentService;
import com.jw.kids.service.KidsSysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsSysParamServiceImpl implements KidsSysParamService {

    @Autowired
    TKidsSysParaDAO tKidsSysParaDAO;
    @Override
    public List findKey(String key, Object value) {
        if(StringUtil.isNotEmpty(key)){
            TKidsSysParaExample example = new TKidsSysParaExample();
            example.createCriteria().andParamKeyEqualTo(key);
            return tKidsSysParaDAO.selectByExample(example);
        }

        return tKidsSysParaDAO.selectByExample(new TKidsSysParaExample());
    }

}
