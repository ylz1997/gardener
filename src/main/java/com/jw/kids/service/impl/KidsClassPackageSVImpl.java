package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TClass;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.bean.TClassPackageExample;
import com.jw.kids.bean.TClassPackageVO;
import com.jw.kids.dao.TClassPackageDAO;
import com.jw.kids.service.KidsClassPackageSV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassPackageSVImpl implements KidsClassPackageSV{

    @Autowired
    TClassPackageDAO tClassPackageDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TClassPackage addClassPackage(TClassPackage TClassPackage) throws GeneralException {
        TClassPackage.setClassPackageId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_teacher")));
        tClassPackageDAO.insert(TClassPackage);
        return TClassPackage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TClassPackage editClassPackage(TClassPackageVO tClassPackageVO) throws GeneralException {
        TClassPackage tClassPackage = new TClassPackage();
        BeanUtils.copyProperties(tClassPackageVO, tClassPackage);
        tClassPackageDAO.updateByPrimaryKey(tClassPackage);
        return tClassPackageVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TClassPackage deleteClassPackage(String classPackageId) throws GeneralException {
        Long lTid = null;
        try{
            lTid = Long.parseLong(classPackageId);
        }catch (Exception e){
            throw new GeneralException("CLASS_PACKAGE_001");
        }
        TClassPackage TClassPackage = tClassPackageDAO.selectByPrimaryKey(lTid);
        TClassPackage.setModfTime(DateUtil.getCurrontTime());
        tClassPackageDAO.deleteByPrimaryKey(lTid);
        return TClassPackage;
    }

    @Override
    public TClassPackage getClassPackageById(Long tId) throws GeneralException {
        return tClassPackageDAO.selectByPrimaryKey(tId);
    }

    @Override
    public List<TClassPackage> listClassPackage(TClassPackage TClassPackage, Integer start, Integer length) throws GeneralException {

        TClassPackageExample example = getExampleByBean(TClassPackage);
        PageHelper.offsetPage(start, length);

        return tClassPackageDAO.selectByExample(example);
    }

    @Override
    public Integer totalClassPackage(TClassPackage TClassPackage) throws GeneralException {
        TClassPackageExample example = getExampleByBean(TClassPackage);
        return tClassPackageDAO.selectByExample(example).size();
    }

    private TClassPackageExample getExampleByBean(TClassPackage TClassPackage){
        TClassPackageExample example = new TClassPackageExample();
        TClassPackageExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" modf_Time desc ");
        if(StringUtil.isNotEmpty(TClassPackage.getClassPackageNm())){
            criteria.andClassPackageNmLike("%" + TClassPackage.getClassPackageNm() + "%");
        }
        return example;
    }

}
