package com.jw.kids.service.impl;

import com.jw.kids.bean.TKidsSysParaExample;
import com.jw.kids.dao.TKidsSysParaDAO;
import com.jw.kids.service.KidsStudentService;
import com.jw.kids.service.KidsSysParamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jw
 * @desc
 */
public class KidsSysParamServiceImpl implements KidsSysParamService {

    @Autowired
    TKidsSysParaDAO tKidsSysParaDAO;
    @Override
    public List findKey(String key, Object value) {
        TKidsSysParaExample example = new TKidsSysParaExample();
        example.createCriteria().andParamKeyEqualTo(key);
        return tKidsSysParaDAO.selectByExample(example);
    }
}
