package com.jw.kids.service.impl;

import com.jw.base.BasicUtil;
import com.jw.base.Constants;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.*;
import com.jw.kids.dao.ClassManageDAO;
import com.jw.kids.dao.TClassLogDAO;
import com.jw.kids.dao.TClassLogDetailDAO;
import com.jw.kids.dao.TKidsDAO;
import com.jw.kids.service.KidsClassLogSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassLogSVImpl implements KidsClassLogSV {

    @Autowired
    private TClassLogDAO classLogDao;
    @Autowired
    private TClassLogDetailDAO classLogDetailDAO;
    @Autowired
    private ClassManageDAO classManageDAO;
    @Autowired
    private TKidsDAO tKidsDAO;


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


        for(TClassLogDetail teacher :tClassLog.getTeacherList()){
            TClassLogDetail tClassLogDetail = new TClassLogDetail();
            tClassLogDetail.setCrtTime(DateUtil.getCurrontTime());
            tClassLogDetail.setDetailLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class_log_detail")));
            tClassLogDetail.setLogId(tClassLog.getLogId());
            tClassLogDetail.setLogObjId(teacher.getLogObjId());
            tClassLogDetail.setLogType(Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYEP_TEACHER);
            tClassLogDetail.setClassTime(tClassLog.getClassTime());
            classLogDetailDAO.insert(tClassLogDetail);
        }

        for(TClassLogDetail tKids :tClassLog.getKidsList()){
            TClassLogDetail tClassLogDetail = new TClassLogDetail();
            tClassLogDetail.setCrtTime(DateUtil.getCurrontTime());
            tClassLogDetail.setDetailLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class_log_detail")));
            tClassLogDetail.setLogId(tClassLog.getLogId());
            tClassLogDetail.setLogType(Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYPE_KIDS);
            tClassLogDetail.setLogObjId(tKids.getLogObjId());
            tClassLogDetail.setClassTime(tClassLog.getClassTime());
            classLogDetailDAO.insert(tClassLogDetail);

            //消掉学生的课时
            TKids tKidsConsume = tKidsDAO.selectByPrimaryKey(tClassLogDetail.getLogObjId());
            tKidsConsume.setAmount(tKidsConsume.getAmount() - 1);
            tKidsDAO.updateByPrimaryKey(tKidsConsume);
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

/*    @Override
    public TClassLog get(Long id) throws GeneralException {
        return classLogDao.selectByPrimaryKey(id);
    }*/

    @Override
    public List<Map> list(TClassLog tClassLog, Integer start, Integer length) throws GeneralException {

        HashMap params = new HashMap<>();
        if(tClassLog != null){
            if((tClassLog.getClassId() != null) && (tClassLog.getClassId()!=-1l)){
                params.put("classId", tClassLog.getClassId());
            }
            if(tClassLog.getLogId() != null){
                params.put("logId", tClassLog.getLogId());
            }
        }
        params.put("start", start);
        params.put("end", start+length);
        return classManageDAO.listClassLog(params);
    }

    @Override
    public Integer total(TClassLog tClassLog) throws GeneralException {
        HashMap params = new HashMap<>();
        if(tClassLog != null){
            if((tClassLog.getClassId() != null) && (tClassLog.getClassId()!=-1l)){
                params.put("classId", tClassLog.getClassId());
            }
            if(tClassLog.getLogId() != null){
                params.put("logId", tClassLog.getLogId());
            }
        }
        return classManageDAO.listClassLog(params).size();
    }

    @Override
    public TClassLogVO get(Long logId) throws InvocationTargetException, IllegalAccessException {
        TClassLogVO result = new TClassLogVO();
        TClassLog resultBean = classLogDao.selectByPrimaryKey(logId);
        BeanUtils.copyProperties(resultBean, result);

        TClassLogDetailExample teacherExample = new TClassLogDetailExample();
        teacherExample.createCriteria().andLogIdEqualTo(result.getLogId()).andLogTypeEqualTo(Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYEP_TEACHER);
        result.setTeacherList(classLogDetailDAO.selectByExample(teacherExample));

        TClassLogDetailExample KidsExample = new TClassLogDetailExample();
        teacherExample.createCriteria().andLogIdEqualTo(result.getLogId()).andLogTypeEqualTo(Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYPE_KIDS);
        result.setKidsList(classLogDetailDAO.selectByExample(teacherExample));
        return result;
    }
}
