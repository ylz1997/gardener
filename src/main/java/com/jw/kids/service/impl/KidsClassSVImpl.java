package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.*;
import com.jw.kids.dao.TClassDAO;
import com.jw.kids.dao.TClassPackageDAO;
import com.jw.kids.dao.TClassSchduleDAO;
import com.jw.kids.service.KidsClassSV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassSVImpl implements KidsClassSV{

    @Autowired
    private TClassDAO tClassDAO;
/*    @Autowired
    private TClassSchduleDAO tClassSchduleDAO;
    @Autowired
    private TClassPackageDAO tClassPackageDAO;*/

    @Override
    @Transactional
    public TClass addClass(TClass tClass) throws GeneralException {
        tClass.setClassId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class")));
        tClassDAO.insert(tClass);
        return tClass;
    }

    @Transactional
    @Override
    public TClass editClass(TClass tClassVO) throws GeneralException {
        TClass tClass = new TClass();
        BeanUtils.copyProperties(tClassVO, tClass);
        tClassDAO.updateByPrimaryKey(tClass);

        return tClassVO;
    }

    @Override
    @Transactional
    public TClass deleteClass(String classId) throws GeneralException {
        Long lId;
        try{
            lId = Long.parseLong(classId);
        }catch (Exception e){
            throw new GeneralException("CLASS_001");
        }
        TClass tClass = tClassDAO.selectByPrimaryKey(lId);
        tClassDAO.deleteByPrimaryKey(lId);
        return tClass;
    }

    @Override
    public TClass getClassById(Long classId) throws GeneralException {
        return tClassDAO.selectByPrimaryKey(classId);
    }

    @Override
    public List<TClass> listClass(TClass tClassVOCondition, Integer start, Integer length) throws GeneralException {
        TClass tClassCondition = new TClass();
        BeanUtils.copyProperties(tClassVOCondition, tClassCondition);
        int page = start/length + 1;

        TClassExample example = getExampleByBean(tClassCondition);
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

        return tClassDAO.selectByExample(example);
    }

    @Override
    public Integer totalClass(TClass tClass) throws GeneralException {
        TClassExample example = getExampleByBean(tClass);
        return tClassDAO.selectByExample(example).size();
    }

    private TClassExample getExampleByBean(TClass TClass){
        TClassExample example = new TClassExample();
        TClassExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" modf_Time desc ");
        if(StringUtil.isNotEmpty(TClass.getClassNm())){
            criteria.andClassNmLike("%" + TClass.getClassNm() + "%");
        }
        return example;
    }
}
