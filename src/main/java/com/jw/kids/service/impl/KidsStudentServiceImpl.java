package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.bean.TKids;
import com.jw.kids.bean.TKidsExample;
import com.jw.kids.dao.TClassPackageDAO;
import com.jw.kids.dao.TKidsDAO;
import com.jw.kids.service.KidsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsStudentServiceImpl implements KidsStudentService {

    @Autowired
    private TKidsDAO tKidsDAO;
    @Autowired
    private TClassPackageDAO tClassPackageDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TKids addKids(TKids tKids) throws GeneralException {
        tKids.setkId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_kids")));
        tKids.setAmount(0);
        tKidsDAO.insert(tKids);
        return tKids;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TKids editKids(TKids tKids) throws GeneralException {
        tKidsDAO.updateByPrimaryKey(tKids);
        return tKids;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TKids deleteKids(String kId) throws GeneralException {
        Long lKid;
        try{
            lKid = Long.parseLong(kId);
        }catch (Exception e){
            throw new GeneralException("KIDS_001");
        }
        TKids tKids = tKidsDAO.selectByPrimaryKey(lKid);
        tKids.setModfTime(DateUtil.getCurrontTime());
        tKidsDAO.deleteByPrimaryKey(lKid);
        return tKids;
    }

    @Override
    public TKids getKidsById(Long kId) throws GeneralException {
        return tKidsDAO.selectByPrimaryKey(kId);
    }

    @Override
    public List<TKids> listKids(TKids tKids,Integer start, Integer limit) throws GeneralException {
        TKidsExample example = getExampleByBean(tKids);
        PageHelper.offsetPage(start, limit);
        return tKidsDAO.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap charge(String kid, String classPackageId) throws GeneralException {
        Long lKid;
        Long lClassPackageId;
        try{
            lKid = Long.parseLong(kid);
            lClassPackageId = Long.parseLong(classPackageId);
        }catch (Exception e){
            throw new GeneralException("KIDS_001");
        }
        TKids tkids = tKidsDAO.selectByPrimaryKey(lKid);
        TClassPackage tClassPackage = tClassPackageDAO.selectByPrimaryKey(lClassPackageId);
        tkids.setAmount(tkids.getAmount()+tClassPackage.getAmount());
        tKidsDAO.updateByPrimaryKey(tkids);
        HashMap map = new HashMap<>();
        map.put("tKids", tkids);
        map.put("tClassPackage", tClassPackage);
        return map;

    }

    public Integer totalKids(TKids tKids){
        TKidsExample example = getExampleByBean(tKids);
        return tKidsDAO.selectByExample(example).size();
    }

    private TKidsExample getExampleByBean(TKids tKids){
        TKidsExample example = new TKidsExample();
        TKidsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" modf_Time desc ");
        if(StringUtil.isNotEmpty(tKids.getChNm())){
            criteria.andChNmLike("%" +tKids.getChNm()+ "%");
        }
        if(StringUtil.isNotEmpty(tKids.getEnNm())){
            criteria.andEnNmLike("%" +tKids.getEnNm()+ "%");
        }
        if(null != tKids.getClassId() && -1 != tKids.getClassId()){
            criteria.andClassIdEqualTo(tKids.getClassId());
        }
        return example;
    }


}
