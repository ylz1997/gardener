package com.jw.kids.service.impl;

import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TKids;
import com.jw.kids.dao.TKidsDAO;
import com.jw.kids.service.KidsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsStudentServiceImpl implements KidsStudentService {

    @Autowired
    TKidsDAO tKidsMapper;
    @Override
    public TKids addStudent(TKids tKids) throws GeneralException {
        tKids.setkId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_kids")));
        tKidsMapper.insert(tKids);
        return null;
    }
}
