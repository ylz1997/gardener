package com.jw.consult.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.Constants;
import com.jw.base.GeneralException;
import com.jw.consult.bean.TKmConsultApply;
import com.jw.consult.bean.TKmConsultApplyExample;
import com.jw.consult.bean.TKmConsultApplyVO;
import com.jw.consult.dao.TKmConsultApplyDAO;
import com.jw.consult.dao.TKmConsultRowsXxxDAO;
import com.jw.consult.service.IKmConsultSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 咨询表sv实现类
 *
 * @author ylz1997  2018-02-28 11:19:12
 */
@Service
public class KmConsultSVImpl implements IKmConsultSV {

    @Autowired
    private TKmConsultApplyDAO tKmConsultApplyDAO;

    @Autowired
    private TKmConsultRowsXxxDAO tKmConsultRowsXxxDAO;

    /**
     * 查询咨询表列表
     *
     * @param tKmConsultApplyVO
     * @param page
     * @param limit
     * @return
     * @throws GeneralException
     */
    @Override
    public Map<String, Object> listConsultApply(TKmConsultApplyVO tKmConsultApplyVO, Integer page, Integer limit) throws GeneralException {
        TKmConsultApplyExample example = new TKmConsultApplyExample();
        TKmConsultApplyExample.Criteria criteria = example.createCriteria();

        //咨询表名称
        if(StringUtil.isNotEmpty(tKmConsultApplyVO.getCnslNm())){
            criteria.andCnslNmLike("%" + tKmConsultApplyVO.getCnslNm() + "%");
        }

        //地区
        criteria.andRegnIdEqualTo(tKmConsultApplyVO.getRegnId());

        //采编人
        if(StringUtil.isNotEmpty(tKmConsultApplyVO.getOpPrsnId())){
            criteria.andOpPrsnIdEqualTo(tKmConsultApplyVO.getOpPrsnId());
        }

        //开始时间
        if(tKmConsultApplyVO.getStartTime() != null){
            criteria.andModfTimeGreaterThanOrEqualTo(tKmConsultApplyVO.getStartTime());
        }

        //结束时间
        if(tKmConsultApplyVO.getEndTime() != null){
            criteria.andModfTimeLessThanOrEqualTo(tKmConsultApplyVO.getEndTime());
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
        int total = tKmConsultApplyDAO.countByExample(example);
        PageHelper.offsetPage((newPage - 1) * limit, newLimit);

        example.setOrderByClause("MODF_TIME DESC");
        List<TKmConsultApply> tKmConsultApplies = tKmConsultApplyDAO.selectByExample(example);

        Map<String, Object> map = new HashMap<>(2);
        map.put("list", tKmConsultApplies);
        map.put("total", total);
        return map;
    }

    /**
     * 新增咨询表
     *
     * @param tKmConsultApply
     * @throws GeneralException
     */
    @Override
    public Long addConsult(TKmConsultApply tKmConsultApply) throws GeneralException {
        //校验名称是否重复
        boolean b = checkConsultNm(tKmConsultApply.getCnslId(), tKmConsultApply.getCnslNm(), tKmConsultApply.getRegnId());
        if(!b){
            throw new GeneralException("124105");
        }

        Long cnslId = Long.valueOf(BasicUtil.getKeysInstant().getSequence("t_km_consult_apply"));

        tKmConsultApply.setCnslId(cnslId);
        tKmConsultApply.setCrtTime(new Timestamp(System.currentTimeMillis()));
        tKmConsultApply.setModfTime(new Timestamp(System.currentTimeMillis()));

        tKmConsultApplyDAO.saveSelective(tKmConsultApply);

        return cnslId;
    }

    /**
     * 修改咨询表
     *
     * @param tKmConsultApply
     * @throws GeneralException
     */
    @Override
    public void updateConsult(TKmConsultApply tKmConsultApply) throws GeneralException {
        //校验名称是否重复
        boolean b = checkConsultNm(tKmConsultApply.getCnslId(), tKmConsultApply.getCnslNm(), tKmConsultApply.getRegnId());
        if(!b){
            throw new GeneralException("124105");
        }
        tKmConsultApply.setModfTime(new Timestamp(System.currentTimeMillis()));
        tKmConsultApplyDAO.updateByPrimaryKeySelective(tKmConsultApply);
    }

    /**
     * 删除咨询表
     *
     * @param cnslId
     * @return
     * @throws GeneralException
     */
    @Override
    public void deleteConsult(Long cnslId, String regnId) throws GeneralException {
        //查询当前表是否存在
        TKmConsultApply consultApply = tKmConsultApplyDAO.selectByPrimaryKey(cnslId);
        if(consultApply == null){
            throw new GeneralException("124106");
        }
        //查询当前表是否有记录
        Map<String, Object> condition = new HashMap<>();
        condition.put("cnslId", cnslId);
        condition.put("tabNm", Constants.NGKM_CONSULT_PARAM.CONSULT_ROW_PREFIX + regnId);
        int consultCount = tKmConsultRowsXxxDAO.getConsultCount(condition);
        if(consultCount == 0){
            tKmConsultApplyDAO.deleteByPrimaryKey(cnslId);
        }else{
            throw new GeneralException("124107");
        }
    }

    /**
     * 获取咨询表详情
     *
     * @param cnslId
     * @return
     * @throws GeneralException
     */
    @Override
    public TKmConsultApply getConsultContent(Long cnslId) throws GeneralException {
        return tKmConsultApplyDAO.selectByPrimaryKey(cnslId);
    }

    /**
     * 校验咨询表名称
     *
     * @param cnslId
     * @param cnslNm
     * @param regnId
     * @return
     * @throws GeneralException
     */
    @Override
    public boolean checkConsultNm(Long cnslId, String cnslNm, String regnId) throws GeneralException {
        TKmConsultApplyExample example = new TKmConsultApplyExample();
        TKmConsultApplyExample.Criteria criteria = example.createCriteria();
        criteria.andCnslNmEqualTo(cnslNm);
        criteria.andRegnIdEqualTo(regnId);

        List<TKmConsultApply> tKmConsultApplies = tKmConsultApplyDAO.selectByExample(example);

        if(tKmConsultApplies == null || tKmConsultApplies.isEmpty()){
            return true;
        }

        //咨询表id为null  查询列表不为空 说明名称重复
        if(cnslId == null){
            return false;
        }

        //咨询表id不为null 遍历查询结果  如果有咨询表id与查出数据的id不一致的  说明名称重复
        boolean flag = true;
        for(TKmConsultApply consultApply : tKmConsultApplies){
            if(!cnslId.equals(consultApply.getCnslId())){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 查询咨询表id的集合
     *
     * @param condition
     * @return
     */
    @Override
    public List<Long> getConsultIds(Map<String, Object> condition) {
        return tKmConsultApplyDAO.getConsultIds(condition);
    }

}
