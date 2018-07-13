package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.*;
import com.jw.kids.dao.TTeacherClassRelDAO;
import com.jw.kids.dao.TTeacherDAO;
import com.jw.kids.service.KidsStaffSV;
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
public class KidsStaffSVImpl implements KidsStaffSV{

    @Autowired
    TTeacherDAO teacherDAO;
    @Autowired
    TTeacherClassRelDAO teacherClassRelDAO;

    @Override
    public TTeacher addStaff(TTeacher tTeacher) throws GeneralException {
        tTeacher.setTeacherId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_teacher")));
        teacherDAO.insert(tTeacher);
        return tTeacher;
    }

    @Override
    public TTeacherVO editStaff(TTeacherVO tTeacher) throws GeneralException {
        teacherDAO.updateByPrimaryKey(tTeacher);

        TTeacherClassRelExample relExample = new TTeacherClassRelExample();
        relExample.createCriteria().andTeacherIdEqualTo(tTeacher.getTeacherId());
        teacherClassRelDAO.deleteByExample(relExample);

        for(TTeacherClassRel tTeacherClassRel: tTeacher.getClassIdArray()){
            tTeacherClassRel.setRlId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_teacher_class_rel")));
            tTeacherClassRel.setCrtTime(DateUtil.getCurrontTime());
            tTeacherClassRel.setModfTime(DateUtil.getCurrontTime());
            tTeacherClassRel.setTeacherId(tTeacher.getTeacherId());
            teacherClassRelDAO.insert(tTeacherClassRel);
        }

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
        //删除关系表
        TTeacherClassRelExample relExample = new TTeacherClassRelExample();
        relExample.createCriteria().andTeacherIdEqualTo(lTid);
        teacherClassRelDAO.deleteByExample(relExample);
        //删除员工表
        TTeacher tTeacher = teacherDAO.selectByPrimaryKey(lTid);
        tTeacher.setModfTime(DateUtil.getCurrontTime());
        teacherDAO.deleteByPrimaryKey(lTid);
        return tTeacher;
    }

    @Override
    public TTeacherVO getStaffById(Long tId) throws GeneralException {
        TTeacher teacher = teacherDAO.selectByPrimaryKey(tId);
        TTeacherVO teacherVO = new TTeacherVO();
        BeanUtils.copyProperties(teacher, teacherVO);

        TTeacherClassRelExample example = new TTeacherClassRelExample();
        example.createCriteria().andTeacherIdEqualTo(tId);
        List<TTeacherClassRel> tTeacherClassRel = teacherClassRelDAO.selectByExample(example);

        teacherVO.setClassIdArray(tTeacherClassRel);

        return teacherVO;
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
