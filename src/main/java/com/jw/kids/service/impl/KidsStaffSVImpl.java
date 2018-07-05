package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.TTeacher;
import com.jw.kids.bean.TTeacherExample;
import com.jw.kids.dao.TTeacherDAO;
import com.jw.kids.service.KidsStaffSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsStaffSVImpl implements KidsStaffSV{

    @Autowired
    TTeacherDAO teacherDAO;

    @Override
    public TTeacher addStaff(TTeacher tTeacher) throws GeneralException {
        tTeacher.setTeacherId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_teacher")));
        teacherDAO.insert(tTeacher);
        return tTeacher;
    }

    @Override
    public TTeacher editStaff(TTeacher tTeacher) throws GeneralException {
        teacherDAO.updateByPrimaryKey(tTeacher);
        return tTeacher;
    }

    @Override
    public TTeacher deleteStaff(String tId) throws GeneralException {
        Long lTid = null;
        try{
            lTid = Long.parseLong(tId);
        }catch (Exception e){
            throw new GeneralException("STAFF_001");
        }
        TTeacher tTeacher = teacherDAO.selectByPrimaryKey(lTid);
        tTeacher.setModfTime(new Date());
        teacherDAO.deleteByPrimaryKey(lTid);
        return tTeacher;
    }

    @Override
    public TTeacher getStaffById(Long tId) throws GeneralException {
        return teacherDAO.selectByPrimaryKey(tId);
    }

    @Override
    public List<TTeacher> listStaff(TTeacher tTeacher, Integer start, Integer length) throws GeneralException {
        int page = start/length + 1;

        TTeacherExample example = getExampleByBean(tTeacher);
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

        return teacherDAO.selectByExample(example);
    }

    @Override
    public Integer totalStaff(TTeacher tTeacher) throws GeneralException {
        TTeacherExample example = getExampleByBean(tTeacher);
        return teacherDAO.selectByExample(example).size();
    }

    private TTeacherExample getExampleByBean(TTeacher tTeacher){
        TTeacherExample example = new TTeacherExample();
        TTeacherExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" modf_Time desc ");
        if(StringUtil.isNotEmpty(tTeacher.getTeacherNm())){
            criteria.andTeacherNmLike("%" + tTeacher.getTeacherNm() + "%");
        }
        return example;
    }
}
