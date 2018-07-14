package com.jw.kids.service.impl;

import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TKidsLog;
import com.jw.kids.dao.TKidsLogDAO;
import com.jw.kids.service.KidsLogSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsLogSVImpl implements KidsLogSV{
    @Autowired
    private TKidsLogDAO logDAO;

    @Override
    @Transactional
    public boolean add(TKidsLog log) throws GeneralException {
        log.setLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_kids_log")));
        logDAO.insert(log);
        return true;
    }


}
