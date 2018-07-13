package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.jw.base.BasicUtil;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TClassLog;
import com.jw.kids.bean.TClassLogExample;
import com.jw.kids.dao.TClassLogDAO;
import com.jw.kids.service.KidsClassLogSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassLogSVImpl implements KidsClassLogSV {

    @Autowired
    TClassLogDAO classLogDao;

    @Override
    public TClassLog add(TClassLog tClassLog) throws GeneralException {
        tClassLog.setLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_teacher")));
        classLogDao.insert(tClassLog);
        return tClassLog;
    }

    @Override
    public TClassLog edit(TClassLog tClassLog) throws GeneralException {
        classLogDao.updateByPrimaryKey(tClassLog);
        return tClassLog;
    }

    @Override
    public TClassLog delete(String tId) throws GeneralException {
        Long lTid = null;
        try{
            lTid = Long.parseLong(tId);
        }catch (Exception e){
            throw new GeneralException("STAFF_001");
        }
        TClassLog tClassLog = classLogDao.selectByPrimaryKey(lTid);
        tClassLog.setModfTime(DateUtil.getCurrontTime());
        classLogDao.deleteByPrimaryKey(lTid);
        return tClassLog;
    }

    @Override
    public TClassLog get(Long id) throws GeneralException {
        return classLogDao.selectByPrimaryKey(id);
    }

    @Override
    public List<TClassLog> list(TClassLog tClassLog, Integer start, Integer length) throws GeneralException {
        int page = start/length + 1;

        TClassLogExample example = getExampleByBean(tClassLog);
        //分页信息
        Integer newPage = page;
        Integer newLimit = length;

        if(newPage == null){
            newPage = 1;
        }

        if(newLimit == null || newLimit == 0){
            newLimit = 10;
        }
        PageHelper.offsetPage((newPage - 1) * length, newLimit);

        return classLogDao.selectByExample(example);
    }

    @Override
    public Integer total(TClassLog TClassLog) throws GeneralException {
        TClassLogExample example = getExampleByBean(TClassLog);
        return classLogDao.selectByExample(example).size();
    }

    private TClassLogExample getExampleByBean(TClassLog tClassLog){
        TClassLogExample example = new TClassLogExample();
        TClassLogExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" modf_Time desc ");
  /*      if(StringUtil.isNotEmpty(tClassLog.getcl())){
            criteria.andTeacherNmLike("%" + TClassLog.geTClassLogNm() + "%");
        }*/
        return example;
    }
}