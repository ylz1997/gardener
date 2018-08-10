package com.jw.kids.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.kids.bean.*;
import com.jw.kids.dao.TClassDAO;
import com.jw.kids.dao.TClassPackageDAO;
import com.jw.kids.dao.TClassSchduleDAO;
import com.jw.kids.dao.TTeacherClassRelDAO;
import com.jw.kids.service.KidsClassSV;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.jw.base.Constants.KIDS_CLASS_TEACHER_LOG_REL_TYPE_CD.CLASS_TEACHER_LOG_REL_TEACHER;

/**
 * @author jw
 * @desc
 */
@Service
public class KidsClassSVImpl implements KidsClassSV{

    @Autowired
    private TClassDAO tClassDAO;
    @Autowired
    private TTeacherClassRelDAO tTeacherClassRelDAO;
/*    @Autowired
    private TClassSchduleDAO tClassSchduleDAO;
    @Autowired
    private TClassPackageDAO tClassPackageDAO;*/

    @Override
    @Transactional
    public TClass addClass(TClassVO tClassVO) throws GeneralException {
        tClassVO.setClassId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_class")));
        tClassDAO.insert(tClassVO);

        TTeacherClassRel tTeacherClassRel = new TTeacherClassRel();
        tTeacherClassRel.setRlId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_teacher_class_rel")));
        tTeacherClassRel.setTeacherId(tClassVO.getTeacherId());
        tTeacherClassRel.setClassId(tClassVO.getClassId());
        tTeacherClassRel.setRlType(CLASS_TEACHER_LOG_REL_TEACHER);
        tTeacherClassRel.setCrtTime(DateUtil.getCurrontTime());
        tTeacherClassRel.setModfTime(tTeacherClassRel.getCrtTime());
        tTeacherClassRelDAO.insert(tTeacherClassRel);
        return tClassVO;
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
    public TClassVO getClassById(Long classId) throws GeneralException {
        TClass tClass = tClassDAO.selectByPrimaryKey(classId);
        TClassVO vo = new TClassVO();
        BeanUtils.copyProperties(tClass,vo);
        TTeacherClassRelExample tTeacherClassRelExample = new TTeacherClassRelExample();
        tTeacherClassRelExample.createCriteria().andClassIdEqualTo(vo.getClassId());
        List<TTeacherClassRel> listR = tTeacherClassRelDAO.selectByExample(tTeacherClassRelExample);
        if (listR.size() > 1) {
            throw new GeneralException("CLASS_002");
        }
        if(listR.size() > 0){
            vo.setTeacherId(listR.get(0).getTeacherId());
        }
        return vo;
    }

    @Override
    public List<TClassVO> listClass(TClass tClassVOCondition, Integer start, Integer length) throws GeneralException {
        TClass tClassCondition = new TClass();
        BeanUtils.copyProperties(tClassVOCondition, tClassCondition);

        TClassExample example = getExampleByBean(tClassCondition);
        PageHelper.offsetPage(start, length);

        List<TClass> list = tClassDAO.selectByExample(example);
        List<TClassVO> listVo = new ArrayList<>();
        for(TClass tClass: list){
            TClassVO vo = new TClassVO();
            BeanUtils.copyProperties(tClass,vo);
            TTeacherClassRelExample tTeacherClassRelExample = new TTeacherClassRelExample();
            tTeacherClassRelExample.createCriteria().andClassIdEqualTo(vo.getClassId());
            List<TTeacherClassRel> listR = tTeacherClassRelDAO.selectByExample(tTeacherClassRelExample);
            if (listR.size() > 1) {
                throw new GeneralException("CLASS_002");
            }
            if(listR.size() > 0){
                vo.setTeacherId(listR.get(0).getTeacherId());
            }
            listVo.add(vo);
        }
        return listVo;
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
