package com.jw.consult.service.impl;

import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.Constants;
import com.jw.base.DateUtil;
import com.jw.base.GeneralException;
import com.jw.consult.bean.*;
import com.jw.consult.dao.TKmConsultApplyDAO;
import com.jw.consult.dao.TKmConsultKeysDAO;
import com.jw.consult.dao.TKmConsultTmpltDAO;
import com.jw.consult.service.IKmConsultTmpltSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class KmConsultTmpltSVImpl implements IKmConsultTmpltSV {
    @Autowired
    private TKmConsultTmpltDAO tKmConsultTmpltDAO;
    @Autowired
    private TKmConsultKeysDAO tKmConsultKeysDAO;
    @Autowired
    private TKmConsultApplyDAO tKmConsultApplyDAO;

  /*  @Autowired
    private IKmConsultTmpltCacheSV iKmConsultTmpltCacheSV;*/
/*    @Autowired
    private ITKmDistrictConfigSV districtConfigSV;*/
    private Logger logger = LoggerFactory.getLogger(KmConsultTmpltSVImpl.class);

    /**
     * 查询模板列表
     * @param tKmConsultTmplt
     * @return
     */
    @Override
    public List<TKmConsultTmplt> getConsultTmpltList(final Integer limit, final Integer page, final TKmConsultTmplt tKmConsultTmplt) {
        Map paramMap = new HashMap();
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getTmpltNm())){
            paramMap.put("tmpltNm","%"+tKmConsultTmplt.getTmpltNm()+"%");
        }
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getAuthRegn())){
            paramMap.put("authRegn",tKmConsultTmplt.getAuthRegn());
        }
        if(tKmConsultTmplt.getCatlId()!=null){
            paramMap.put("catlId",tKmConsultTmplt.getCatlId());
        }
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getOpPrsnId())){
            paramMap.put("opPrsnId",tKmConsultTmplt.getOpPrsnId());
        }
        if(tKmConsultTmplt.getStartTime() != null){
            paramMap.put("startTime",tKmConsultTmplt.getStartTime());
        }
        if(tKmConsultTmplt.getEndTime() != null){
            paramMap.put("endTime",tKmConsultTmplt.getEndTime());
        }

        Integer newPage = page;
        if(page == null  || page <= 0){
            newPage = 1;
        }
        Integer newLimit = limit;
        if(limit == null || limit <= 0){
            newLimit = 10;
        }
        paramMap.put("limit", newLimit);
        paramMap.put("start", (newPage - 1) * newLimit);

        List<TKmConsultTmplt> tKmConsultTmplts = tKmConsultTmpltDAO.getConsultTmpltList(paramMap);
        if(!tKmConsultTmplts.isEmpty()){                                                                            //为每一个咨询表设置适用地区值/名转换
            for(TKmConsultTmplt consultTmplt : tKmConsultTmplts){
                String authRegnStr = consultTmplt.getAuthRegn().trim();
                if(!"".equals(authRegnStr)){
                    String[] strs = authRegnStr.split(",");
                    String nms = "";
/*                    for (int i = 0;i<strs.length;i++){
                        TKmDistrictConfig districtConfig= districtConfigSV.getByPrimaryKey(strs[i]);
                        nms += districtConfig.getRegnNm() + ",";
                        if(i==strs.length-1){
                            nms = nms.substring(0,nms.length()-1);
                        }
                    }*/
                    consultTmplt.setAuthRegnNm(nms);
                }

            }
        }

        return tKmConsultTmplts;
    }

    /**
     * 查询模板列表总数
     * @param tKmConsultTmplt
     * @return
     */
    @Override
    public int getConsultTmpltListCount(TKmConsultTmplt tKmConsultTmplt) {
        Map paramMap = new HashMap();
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getTmpltNm())){
            paramMap.put("tmpltNm","%"+tKmConsultTmplt.getTmpltNm()+"%");
        }
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getAuthRegn())){
            paramMap.put("authRegn",tKmConsultTmplt.getAuthRegn());
        }
        if(tKmConsultTmplt.getCatlId()!=null){
            paramMap.put("catlId",tKmConsultTmplt.getCatlId());
        }
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getOpPrsnId())){
            paramMap.put("opPrsnId",tKmConsultTmplt.getOpPrsnId());
        }
        if(tKmConsultTmplt.getStartTime() != null){
            paramMap.put("startTime",tKmConsultTmplt.getStartTime());
        }
        if(tKmConsultTmplt.getEndTime() != null){
            paramMap.put("endTime",tKmConsultTmplt.getEndTime());
        }
        int count = tKmConsultTmpltDAO.getConsultTmpltListCount(paramMap);
        return count;
    }
    @Override
    public List listTKmConsultKeys(Long tmpltId, int startRow, int pageSize) {
        TKmConsultKeysExample example = new TKmConsultKeysExample();
        example.setStartRow(startRow);
        example.setPageSize(pageSize);
        TKmConsultKeysExample.Criteria criteria = example.createCriteria();
        criteria.andTmpltIdEqualTo(tmpltId);
        example.setOrderByClause(" ARGE_SEQNO,MODF_TIME DESC ");
        return tKmConsultKeysDAO.selectByExample(example);
    }

    @Override
    public TKmConsultKeys getConsultTmpltColumn(Long columnId) {
        return tKmConsultKeysDAO.selectByPrimaryKey(columnId);
    }

    @Override
    public List listTKmConsultKeys(Long tmpltId) {
        TKmConsultKeysExample example = new TKmConsultKeysExample();
        TKmConsultKeysExample.Criteria criteria = example.createCriteria();
        criteria.andTmpltIdEqualTo(tmpltId);
        example.setOrderByClause(" ARGE_SEQNO,MODF_TIME DESC ");
        return tKmConsultKeysDAO.selectByExample(example);
    }

    @Override
    public List<TKmConsultTmplt> getConsultTmpltList(TKmConsultTmplt tKmConsultTmplt){
        if(StringUtil.isNotEmpty(tKmConsultTmplt.getTmpltNm())){
            tKmConsultTmplt.setTmpltNm("%" + tKmConsultTmplt.getTmpltNm() + "%");
        }
        return tKmConsultTmpltDAO.getTmpltListNoPage(tKmConsultTmplt);
    }

    @Override
    public List<TKmConsultTmplt> getConsultTmpltList(TKmConsultTmplt tKmConsultTmplt, int startRow, int pageSize){
        TKmConsultTmpltExample example = new TKmConsultTmpltExample();
        example.setStartRow(startRow);
        example.setPageSize(pageSize);
        TKmConsultTmpltExample.Criteria criteria = example.createCriteria();
        criteria.andTmpltNmLike(tKmConsultTmplt.getTmpltNm());
        return tKmConsultTmpltDAO.selectByExample(example);
    }

    @Override
    public TKmConsultTmplt getConsultTmplt(Long tmpltId){
        TKmConsultTmplt tKmConsultTmplt = tKmConsultTmpltDAO.selectByPrimaryKey(tmpltId);
        if(tKmConsultTmplt == null){
            return null;
        }
        return tKmConsultTmplt;
    }

    public Long saveConsultTmplt(TKmConsultTmplt tKmConsultTmplt) throws GeneralException {
        tKmConsultTmplt.setTmpltId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_km_consult_tmplt")));
        tKmConsultTmplt.setCrtTime(DateUtil.getCurrontTime());
        tKmConsultTmplt.setModfTime(DateUtil.getCurrontTime());
        tKmConsultTmpltDAO.save(tKmConsultTmplt);
        return tKmConsultTmplt.getTmpltId();
    }

    @Override
    public Long saveOrUpdateConsultTmpltColm(TKmConsultKeys tKmConsultKeys) throws GeneralException {
        //新增
        if(tKmConsultKeys.getColmId() == null){

            judgeNameRepeat(tKmConsultKeys.getColmNm(), tKmConsultKeys.getTmpltId());
            judgeCodeRepeat(tKmConsultKeys.getColmCode(), tKmConsultKeys.getTmpltId());
            judgeTypeRepeat(tKmConsultKeys.getColmTypeCd(), tKmConsultKeys.getTmpltId());
            tKmConsultKeys.setColmId(Long.parseLong(BasicUtil.getKeysInstant().getSequence("t_km_consult_keys")));
            tKmConsultKeys.setCrtTime(DateUtil.getCurrontTime());
            tKmConsultKeys.setModfTime(DateUtil.getCurrontTime());
            tKmConsultKeys.setArgeSeqno(new Short("0"));
            tKmConsultKeysDAO.save(tKmConsultKeys);
        }
        //修改
        else{
            //验证名称、编码是否被修改
            TKmConsultKeys keysTest = tKmConsultKeysDAO.selectByPrimaryKey(tKmConsultKeys.getColmId());
            if(!keysTest.getColmNm().equals(tKmConsultKeys.getColmNm())){
                judgeNameRepeat(tKmConsultKeys.getColmNm(), tKmConsultKeys.getTmpltId());
            }
            if(!keysTest.getColmCode().equals(tKmConsultKeys.getColmCode())){
                judgeCodeRepeat(tKmConsultKeys.getColmCode(), tKmConsultKeys.getTmpltId());
            }

            if(!keysTest.getColmTypeCd().equals(tKmConsultKeys.getColmTypeCd())){
               judgeTypeRepeat(tKmConsultKeys.getColmTypeCd(), tKmConsultKeys.getTmpltId());
            }

            tKmConsultKeysDAO.updateByPrimaryKeyWithBusiness(tKmConsultKeys);
        }
        /*//刷新缓存
        iKmConsultTmpltCacheSV.saveTmpltKeysCache(tKmConsultKeys.getTmpltId());
        */
        return tKmConsultKeys.getColmId();
    }

    @Override
    public Long deleteConsultKeys(String colmId) throws GeneralException{
        Long lColmId = Long.parseLong(colmId);
        //根据字段id查询模板id  留待备用
        TKmConsultKeys tKmConsultKeys = tKmConsultKeysDAO.selectByPrimaryKey(lColmId);
        //查询记录表是否有以该模板的记录
        if(tKmConsultKeys == null){
            throw new GeneralException("114106");
        }
        TKmConsultApplyExample example = new TKmConsultApplyExample();
        example.createCriteria().andTmpltIdEqualTo(tKmConsultKeys.getTmpltId());
        List<TKmConsultApply> tKmConsultApplies = tKmConsultApplyDAO.selectByExample(example);

        if(tKmConsultApplies == null || tKmConsultApplies.isEmpty()){
            tKmConsultKeysDAO.deleteByPrimaryKey(lColmId);
        }else{
            throw new GeneralException("114104");
        }
        /*//刷新咨询表模板原子缓存
        iKmConsultTmpltCacheSV.saveTmpltKeysCache(tKmConsultKeys.getTmpltId());
        */
        return lColmId;
    }


    @Override
    public void modifyArgeSeqno(TKmConsultKeys instance) throws GeneralException{
        TKmConsultKeys tKmConsultKeys = tKmConsultKeysDAO.selectByPrimaryKey(instance.getColmId());
        tKmConsultKeys.setArgeSeqno(new Short(instance.getArgeSeqno()));
        tKmConsultKeys.setModfTime(DateUtil.getCurrontTime());
        tKmConsultKeysDAO.updateByPrimaryKey(tKmConsultKeys);

        /*iKmConsultTmpltCacheSV.saveTmpltKeysCache(tKmConsultKeys.getTmpltId());*/
    }


    /**
     * 判断colmNm是否重复 重名直接抛出异常
     * @param colmNm 列名
     * @return
     */
    private void judgeNameRepeat(String colmNm, Long tmpltId) throws GeneralException {
        TKmConsultKeysExample tKmConsultKeysExample = new TKmConsultKeysExample();
        TKmConsultKeysExample.Criteria criteria = tKmConsultKeysExample.createCriteria();
        criteria.andColmNmEqualTo(colmNm).andTmpltIdEqualTo(tmpltId);
        if(!tKmConsultKeysDAO.selectByExample(tKmConsultKeysExample).isEmpty()){
            logger.error("已经存在的字段名称");
            throw new GeneralException("114101");
        }
    }
    /**
     * 判断colmNm是否重复
     * @param colmCode 列编码
     * @return true:重复;false:不重复
     */
    private void judgeCodeRepeat(String colmCode, Long tmpltId) throws GeneralException {
        TKmConsultKeysExample tKmConsultKeysExample = new TKmConsultKeysExample();
        TKmConsultKeysExample.Criteria criteria = tKmConsultKeysExample.createCriteria();
        criteria.andColmCodeEqualTo(colmCode).andTmpltIdEqualTo(tmpltId);
        if(!tKmConsultKeysDAO.selectByExample(tKmConsultKeysExample).isEmpty()){
            logger.error("已经存在的字段编码");
            throw new GeneralException("114102");
        }
    }

    /**
     * 校验类型是否重复
     *
     * @param colmTypeCd
     * @throws GeneralException
     */
    private void judgeTypeRepeat(String colmTypeCd, Long tmpltId) throws GeneralException{
/*        if(Constants.NGKM_CONSULT_COLM_TYPE.MES.equals(colmTypeCd) || Constants.NGKM_CONSULT_COLM_TYPE.REL.equals(colmTypeCd)){
            TKmConsultKeysExample example = new TKmConsultKeysExample();
            example.createCriteria().andTmpltIdEqualTo(tmpltId).andColmTypeCdEqualTo(colmTypeCd);
            int count = tKmConsultKeysDAO.countByExample(example);
            if(count > 0){
                throw new GeneralException("114107");
            }
        }*/
    }

    @Override
    public Short getMaxArgeSeqnoByCaltId(Long catlId) {
        Short argeSeqno = tKmConsultTmpltDAO.getMaxArgeSeqnoByCaltId(catlId);
        if(argeSeqno==null){
            argeSeqno=0;
        }
        return argeSeqno;
    }

    @Override
    /**
     *  保存咨询表模板基本信息
     * @author wx
     * @param  [tKmConsultTmplt]
     * @return boolean
     */
    public boolean save(TKmConsultTmplt tKmConsultTmplt) throws GeneralException {
        int total = getCountByParams(tKmConsultTmplt);
        if (total != 0) {
            throw new GeneralException("114103");
        }
        Short maxArgeSeqno = getMaxArgeSeqnoByCaltId(tKmConsultTmplt.getCatlId());
        //去除复选框的全选value值
        String authRegn = tKmConsultTmplt.getAuthRegn();
        if(authRegn.indexOf("0,")==0){
            String authRegnAter = authRegn.substring(2);
            tKmConsultTmplt.setAuthRegn(authRegnAter);
        }
        tKmConsultTmplt.setArgeSeqno((short) (maxArgeSeqno + 1));
        tKmConsultTmplt.setCrtTime(new Timestamp(System.currentTimeMillis()));
        tKmConsultTmplt.setModfTime(new Timestamp(System.currentTimeMillis()));
        int cosultTmpltInfoCount = tKmConsultTmpltDAO.save(tKmConsultTmplt);
        if (cosultTmpltInfoCount >= 1) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * 咨询表模板添加相似
     */
    public boolean extendTmpltInfo(TKmConsultTmplt tKmConsultTmplt, String temptId) throws GeneralException {
        Long tmpltIdOld = tKmConsultTmplt.getTmpltId();
        Long newTemptId = Long.valueOf(temptId);
        tKmConsultTmplt.setTmpltId(newTemptId);
        tKmConsultTmplt.setCrtTime(new Timestamp(System.currentTimeMillis()));
        tKmConsultTmplt.setModfTime(new Timestamp(System.currentTimeMillis()));
        boolean isTmpltTrue ;
        try {
            isTmpltTrue = save(tKmConsultTmplt);                //保存咨询表模板基本信息
        } catch (GeneralException e) {
            throw new GeneralException(e.getErrorCode(),e);
        }
        if (isTmpltTrue) {                                         //保存咨询表模板列信息
            String opPrsnId = tKmConsultTmplt.getOpPrsnId();
            Timestamp crtTime = tKmConsultTmplt.getCrtTime();
            Timestamp modfTime = tKmConsultTmplt.getModfTime();
            List<TKmConsultKeys> tKmConsultKeysList = tKmConsultKeysDAO.getCosultTmpltKeysByTmpltId(tmpltIdOld);
            Iterator<TKmConsultKeys> keysIterator = tKmConsultKeysList.iterator();
            while (keysIterator.hasNext()) {
                String colmId = null;
                try {
                    colmId = BasicUtil.getKeysInstant().getSequence("t_km_consult_keys");
                } catch (GeneralException e) {
                    logger.error(e.getMessage(),e);
                }
                Long colmIdL = Long.valueOf(colmId);
                TKmConsultKeys tKmConsultKeys = keysIterator.next();
                tKmConsultKeys.setColmId(colmIdL);
                tKmConsultKeys.setTmpltId(newTemptId);
                tKmConsultKeys.setOpPrsnId(opPrsnId);
                tKmConsultKeys.setCrtTime(crtTime);
                tKmConsultKeys.setModfTime(modfTime);
                tKmConsultKeysDAO.save(tKmConsultKeys);
            }
            //刷新咨询表模板记原子缓存
            /*iKmConsultTmpltCacheSV.saveTmpltKeysCache(Long.valueOf(temptId));
            */return true;                                               //添加相似成功
        }
        return false;
    }

    @Override
    /**
     * 修改咨询表模板
     */
    public boolean updateByPrimaryKeySelective(TKmConsultTmplt tKmConsultTmplt) throws GeneralException {
        int total = getCountByParams(tKmConsultTmplt);
        if (total != 0) {
            throw new GeneralException("114103");
        }
        //去除复选框的全选value值
        String authRegn = tKmConsultTmplt.getAuthRegn();
        if(authRegn.indexOf("0,")==0){
            String authRegnAter = authRegn.substring(2);
            tKmConsultTmplt.setAuthRegn(authRegnAter);
        }
        tKmConsultTmplt.setModfTime(new Timestamp(System.currentTimeMillis()));
        int count = tKmConsultTmpltDAO.updateByPrimaryKeySelective(tKmConsultTmplt);
        if(count>=1){
            return true;
        }
        return false;
    }

    /**
     * 获取相同名称的模板数量
     * @param tKmConsultTmplt type
     * @return int
     * @author
     */
    public int getCountByParams(TKmConsultTmplt tKmConsultTmplt) {
        TKmConsultTmpltExample tKmConsultTmpltExample = new TKmConsultTmpltExample();
        TKmConsultTmpltExample.Criteria criteria = tKmConsultTmpltExample.createCriteria();
        criteria.andTmpltNmEqualTo(tKmConsultTmplt.getTmpltNm());
        criteria.andTmpltIdNotEqualTo(tKmConsultTmplt.getTmpltId());

        return tKmConsultTmpltDAO.countByExample(tKmConsultTmpltExample);
    }

    @Override
    public void deleteByPrimaryKey(Long tmpltId) throws GeneralException{
        TKmConsultApplyExample applyExample = new TKmConsultApplyExample();
        applyExample.or().andTmpltIdEqualTo(tmpltId);
        int count=tKmConsultApplyDAO.countByExample(applyExample);
       // int count = tKmConsultApplyDAO.getCountByTmpltId(tmpltId);
        if(count>=1){
            throw new GeneralException("114104");
        }
        tKmConsultKeysDAO.deleteByTmpltId(tmpltId);
        tKmConsultTmpltDAO.deleteByPrimaryKey(tmpltId);

        /*iKmConsultTmpltCacheSV.deleteTmpltKeys(tmpltId);*/
    }
    @Override
    public List<TKmConsultKeys> getAllKeys(Long tmpltId) {
        TKmConsultKeysExample example = new TKmConsultKeysExample();
        TKmConsultKeysExample.Criteria criteria = example.createCriteria();
        criteria.andTmpltIdEqualTo(tmpltId);
        return tKmConsultKeysDAO.selectByExample(example);
    }

}
