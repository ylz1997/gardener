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
    @Autowired
    private TClassSchduleDAO tClassSchduleDAO;
    @Autowired
    private TClassPackageDAO tClassPackageDAO;
    @Override
    public TClassVO addClass(TClassVO tClassVO) throws GeneralException {
        TClass tClass = new TClass();
        BeanUtils.copyProperties(tClassVO, tClass);
        tClass.setClassId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class")));
        tClassDAO.insert(tClass);

        if(tClassVO.getSchduleList() == null){
            return tClassVO;
        }
        for(TClassSchdule schdule: tClassVO.getSchduleList()){
            BeanUtils.copyProperties(tClassVO, schdule);
            schdule.setSchduleId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class")));
            schdule.setClassId(tClass.getClassId());
            tClassSchduleDAO.insert(schdule);
        }

        return tClassVO;
    }

    @Override
    public TClassVO editClass(TClassVO tClassVO) throws GeneralException {
        TClass tClass = new TClass();
        BeanUtils.copyProperties(tClassVO, tClass);
        tClassDAO.updateByPrimaryKey(tClass);

        if(tClassVO.getSchduleList() == null){
            return tClassVO;
        }
        for(TClassSchdule schdule: tClassVO.getSchduleList()){
            BeanUtils.copyProperties(tClassVO, schdule);
            tClassSchduleDAO.updateByPrimaryKey(schdule);
        }

        return tClassVO;
    }

    @Override
    public TClassVO deleteClass(String classId) throws GeneralException {
        TClassVO vo = new TClassVO();
        Long lId;
        try{
            lId = Long.parseLong(classId);
        }catch (Exception e){
            throw new GeneralException("CLASS_001");
        }
        TClass tClass = tClassDAO.selectByPrimaryKey(lId);
        BeanUtils.copyProperties(tClass, vo);
        tClassDAO.deleteByPrimaryKey(lId);

        TClassSchduleExample example = new TClassSchduleExample();
        example.createCriteria().andClassIdEqualTo(lId);
        vo.setSchduleList(tClassSchduleDAO.selectByExample(example));

        for(TClassSchdule tClassSchdule: vo.getSchduleList()){
            tClassSchduleDAO.deleteByPrimaryKey(tClassSchdule.getSchduleId());
        }
        return vo;
    }

    @Override
    public TClassVO getClassById(Long classId) throws GeneralException {
        TClassVO vo = new TClassVO();
        BeanUtils.copyProperties(tClassDAO.selectByPrimaryKey(classId),vo);

        TClassSchduleExample example = new TClassSchduleExample();
        example.createCriteria().andClassIdEqualTo(vo.getClassId());
        vo.setSchduleList(tClassSchduleDAO.selectByExample(example));

        return vo;
    }

    @Override
    public List<TClassVO> listClass(TClassVO tClassVOCondition, Integer start, Integer length) throws GeneralException {
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

        List<TClassVO> voList = new ArrayList<>();
        for(TClass tClass: tClassDAO.selectByExample(example)){
            TClassVO tClassVO = new TClassVO();
            BeanUtils.copyProperties(tClass, tClassVO);

            TClassSchduleExample schduleExample = new TClassSchduleExample();
            schduleExample.createCriteria().andClassIdEqualTo(tClassVO.getClassId());
            tClassVO.setSchduleList(tClassSchduleDAO.selectByExample(schduleExample));
            voList.add(tClassVO);

            TClassPackage classPackage = tClassPackageDAO.selectByPrimaryKey(tClass.getClassPackageId());
            tClassVO.setAmount(classPackage.getAmount());
            tClassVO.setPrice(classPackage.getPrice());
        }
        return voList;
    }

    @Override
    public Integer totalClass(TClassVO tClassVO) throws GeneralException {
        TClass tClass = new TClass();
        BeanUtils.copyProperties(tClassVO, tClass);
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
