package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TKids;
import com.jw.kids.bean.TKidsExample;
import com.jw.kids.dao.TKidsDAO;
import com.jw.kids.service.KidsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsStudentServiceImpl implements KidsStudentService {

    @Autowired
    TKidsDAO tKidsMapper;
    @Override
    public TKids addKids(TKids tKids) throws GeneralException {
        tKids.setkId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_kids")));
        tKidsMapper.insert(tKids);
        return tKids;
    }

    @Override
    public TKids editKids(TKids tKids) throws GeneralException {
        tKidsMapper.updateByPrimaryKey(tKids);
        return tKids;
    }

    @Override
    public TKids deleteKids(TKids tKids) throws GeneralException {
        tKidsMapper.deleteByPrimaryKey(tKids.getkId());
        return tKids;
    }

    @Override
    public TKids getKidsById(Long kId) throws GeneralException {
        return tKidsMapper.selectByPrimaryKey(kId);
    }

    @Override
    public List<TKids> listKids(TKids tKids,Integer start, Integer limit) throws GeneralException {
        int page = start/limit + 1;
        TKidsExample example = new TKidsExample();
        TKidsExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(tKids.getChNm())){
            criteria.andChNmEqualTo(tKids.getChNm());
        }
        if(StringUtil.isNotEmpty(tKids.getEnNm())){
            criteria.andEnNmEqualTo(tKids.getEnNm());
        }
        if(null != tKids.getClassId()){
            criteria.andClassIdEqualTo(tKids.getClassId());
        }

        //分页信息
        Integer newPage = page;
        Integer newLimit = limit;

        if(newPage == null){
            newPage = 1;
        }

        if(newLimit == null || newLimit == 0){
            newLimit = 10;
        }
        PageHelper.offsetPage((newPage - 1) * limit, newLimit);

        return tKidsMapper.selectByExample(example);
    }

    public Integer totalKids(){
        return tKidsMapper.selectByExample(new TKidsExample()).size();
    }




}
