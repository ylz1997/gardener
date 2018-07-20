package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.Constants;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.*;
import com.jw.kids.dao.TClassLogDAO;
import com.jw.kids.dao.TClassLogDetailDAO;
import com.jw.kids.service.KidsClassLogSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassLogSVImpl implements KidsClassLogSV {

    @Autowired
    TClassLogDAO classLogDao;
    @Autowired
    TClassLogDetailDAO classLogDetailDAO;

    private Logger logger = LoggerFactory.getLogger(KidsClassLogSVImpl.class);



    @Override
    @Transactional
    public TClassLogVO add(TClassLogVO tClassLog) throws GeneralException {
        if(tClassLog == null){
            logger.error("============tClassLog is null==============");
            return null;
        }

        tClassLog.setLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class_log")));
        classLogDao.insert(tClassLog);


        for(TTeacher teacher :tClassLog.getTeacherList()){
            TClassLogDetail tClassLogDetail = new TClassLogDetail();
            tClassLogDetail.setCrtTime(DateUtil.getCurrontTime());
            tClassLogDetail.setDetailLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class_log_detail")));
            tClassLogDetail.setLogId(tClassLog.getLogId());
            tClassLogDetail.setLogObjId(teacher.getTeacherId());
            tClassLogDetail.setLogType(Constants.LOG_OBJ_TYPE_CD.LOG_OBJ_TYEP_TEACHER);
            classLogDetailDAO.insert(tClassLogDetail);
        }

        for(TKids tKids :tClassLog.getKidsList()){
            TClassLogDetail tClassLogDetail = new TClassLogDetail();
            tClassLogDetail.setCrtTime(DateUtil.getCurrontTime());
            tClassLogDetail.setDetailLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class_log_detail")));
            tClassLogDetail.setLogId(tClassLog.getLogId());
            tClassLogDetail.setLogType(Constants.LOG_OBJ_TYPE_CD.LOG_OBJ_TYPE_KIDS);
            tClassLogDetail.setLogObjId(tKids.getkId());

            classLogDetailDAO.insert(tClassLogDetail);
        }

        return tClassLog;
    }

    @Override
    @Transactional
    public TClassLogVO edit(TClassLogVO tClassLog) throws GeneralException {
        classLogDao.updateByPrimaryKey(tClassLog);
        return tClassLog;
    }

    @Override
    @Transactional
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

        return classLogDao.selectByExampleWithBLOBs(example);
    }

    @Override
    public Integer total(TClassLog TClassLog) throws GeneralException {
        TClassLogExample example = getExampleByBean(TClassLog);
        return classLogDao.selectByExampleWithBLOBs(example).size();
    }

    private TClassLogExample getExampleByBean(TClassLog tClassLog){
        TClassLogExample example = new TClassLogExample();
        TClassLogExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" modf_Time desc ");
        if(null != tClassLog.getClassId()){
            criteria.andClassIdEqualTo(tClassLog.getClassId());
        }
        return example;
    }
}
