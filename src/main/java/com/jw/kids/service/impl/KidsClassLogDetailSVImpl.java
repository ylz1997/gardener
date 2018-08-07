package com.jw.kids.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jw.base.BasicUtil;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TClassLogDetail;
import com.jw.kids.dao.ClassManageDAO;
import com.jw.kids.dao.TClassLogDetailDAO;
import com.jw.kids.service.KidsClassLogDetailSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jw.base.Constants.KIDS_LOG_OBJ_TYPE_CD.LOG_OBJ_TYPE_KIDS_READD;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassLogDetailSVImpl implements KidsClassLogDetailSV{

    private Logger logger = LoggerFactory.getLogger("KidsClassLogDetailSVImpl");
    @Autowired
    private ClassManageDAO classManageDAO;
    @Autowired
    private TClassLogDetailDAO tClassLogDetailDAO;

    @Transactional
    @Override
    public TClassLogDetail add(TClassLogDetail tClassLogDetail) throws GeneralException {
        tClassLogDetail.setDetailLogId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("T_CLASS_LOG_DETAIL")));
        tClassLogDetail.setLogType(LOG_OBJ_TYPE_KIDS_READD);
        tClassLogDetail.setCrtTime(DateUtil.getCurrontTime());
        tClassLogDetailDAO.insert(tClassLogDetail);
        return tClassLogDetail;
    }


    @Override
    public HashMap listLogDetail(TClassLogDetail tClassLogDetail, Integer start, Integer end) {
        if(start == null)
            start = 0;
        if(end == null){
            end = 10;
        }
        if(tClassLogDetail == null){
            logger.error("tClassLogDetail = null 入参不正确!");
            return null;
        }
        if(tClassLogDetail.getLogObjId() == null ||
                tClassLogDetail.getLogType() == null){
            logger.error("tClassLogDetail 入参不正确!" + tClassLogDetail);
            return null;
        }
        HashMap map = new HashMap();
        map.put("logObjId", tClassLogDetail.getLogObjId());
        map.put("logType", tClassLogDetail.getLogType());
        map.put("start", start);
        map.put("end", end);

        List<Map> list = classManageDAO.listLogDetail(map);

        HashMap resultMap = new HashMap();
        resultMap.put("data", list);
        return resultMap;
    }

    @Override
    public Integer total(TClassLogDetail tClassLogDetail) {
        if(tClassLogDetail == null){
            logger.error("tClassLogDetail = null 入参不正确!");
            return null;
        }
        if(tClassLogDetail.getLogObjId() == null ||
                tClassLogDetail.getLogType() == null){
            logger.error("tClassLogDetail 入参不正确!" + tClassLogDetail);
            return null;
        }
        HashMap map = new HashMap();
        map.put("logObjId", tClassLogDetail.getLogObjId());
        map.put("logType", tClassLogDetail.getLogType());

        List<Map> list = classManageDAO.listLogDetail(map);
        return list.size();
    }

}
