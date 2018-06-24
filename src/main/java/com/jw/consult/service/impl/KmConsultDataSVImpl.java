package com.jw.consult.service.impl;

import com.github.pagehelper.StringUtil;
import com.jw.base.BasicUtil;
import com.jw.base.Constants;
import com.jw.base.GeneralException;
import com.jw.consult.bean.*;
import com.jw.consult.dao.TKmConsultDataDAO;
import com.jw.consult.dao.TKmConsultKeysDAO;
import com.jw.consult.dao.TKmConsultRowsXxxDAO;
import com.jw.consult.service.IKmConsultDataSV;
import com.jw.consult.service.IKmConsultSV;
import com.jw.consult.service.IKmConsultTmpltSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 咨询表记录service实现
 *
 * @author ylz1997 2018-3-2 15:14:10
 */
@Service
public class KmConsultDataSVImpl implements IKmConsultDataSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(KmConsultDataSVImpl.class);

    @Autowired
    private IKmConsultTmpltSV iKmConsultTmpltSV;

    @Autowired
    private TKmConsultRowsXxxDAO tKmConsultRowsXxxDAO;

    @Autowired
    private TKmConsultDataDAO tKmConsultDataDAO;
    @Autowired
    private TKmConsultKeysDAO tKmConsultKeysDAO;
    @Autowired
    private IKmConsultSV iKmConsultSV;
    private Logger logger = LoggerFactory.getLogger(KmConsultDataSVImpl.class);
    /**
     * 新增或更新咨询表记录
     *
     * @param data
     */
    @Override
    public Long addConsultData(Map<String, Object> data) throws GeneralException{
        Map<String, String> consultData = (Map<String, String>)data.get("data");
        Long tmpltId = (Long)data.get("tmpltId");
        //校验数据
        String s = checkConsultData(consultData, tmpltId, false);
        if(! "".equals(s)){
            logger.error(consultData.toString()+"||数据校验失败||"+s);
            throw new GeneralException(s);
        }
        //执行插入操作
        return saveConsultRow(data, false);
    }

    /**
     * 更新咨询表记录
     *
     * @param data
     */
    @Override
    public void batchUpdateConsultData(Map<String, Object> data) throws GeneralException{
        Map<String, String> consultData = (Map<String, String>)data.get("data");
        Long tmpltId = (Long)data.get("tmpltId");
        //校验数据
        String s = checkConsultData(consultData, tmpltId, true);
        if(! "".equals(s)){
            throw new GeneralException(s);
        }
        //执行插入操作
        saveConsultRow(data, true);
    }

    /**
     * 咨询表记录删除
     *
     * @param recIds 记录id
     */
    @Override
    public void deleteConsultData(List<String> recIds, String regnId) {
        //删除行记录表
        Map<String,Object> condition = new HashMap<>();
        condition.put("tabNm", Constants.NGKM_CONSULT_PARAM.CONSULT_ROW_PREFIX + regnId);
        condition.put("recIds", recIds);
        tKmConsultRowsXxxDAO.deleteConsultRows(condition);
        //获取记录id
        condition.put("tabNm", Constants.NGKM_CONSULT_PARAM.CONSULT_DATA_PREFIX  + regnId);
        List<Long> cnttIds = tKmConsultDataDAO.getCnttIds(condition);
        //删除之前校验是否有内容
        if(cnttIds == null || cnttIds.isEmpty()){
            return;
        }
        //删除记录表
        condition.put("cnttIds", cnttIds);
        tKmConsultDataDAO.deleteMult(condition);
    }

    /**
     *
     * @param searchVO 咨询记录查询vo
     * @return 咨询表对象
     * @throws GeneralException
     */
    @Override
    public ConsultDataVO getConsultApply(ConsultSearchVO searchVO) throws GeneralException {
        ConsultDataVO consultDataVO = new ConsultDataVO();

        Map<Long,InnerListBean> params = buildSearchParaMap(searchVO.getSearchParams());
        TKmConsultApply tKmConsultApply = iKmConsultSV.getConsultContent(searchVO.getCnslId());
        consultDataVO.setCnslId(tKmConsultApply.getCnslId());
        consultDataVO.setCnslNm(tKmConsultApply.getCnslNm());
        consultDataVO.setTmpltId(tKmConsultApply.getTmpltId());
        Map<Long,TKmConsultKeys> keysMap = mapConsultKeysByTmpltId(tKmConsultApply.getTmpltId());
        //同一个咨询表应用下的记录查询
        List<ConsultDataVO.ConsultRowVO> listConsultRow = new ArrayList<>();
        List<TKmConsultRowsXxx> listTKmConsultRows = listConsultRows(tKmConsultApply);
        //加载咨询表记录
        for(TKmConsultRowsXxx tKmConsultRowsXxx: listTKmConsultRows){
            ConsultDataVO.ConsultRowVO rowVO = consultDataVO.createConsultRow();
            rowVO.setRecId(tKmConsultRowsXxx.getRecId());
            rowVO.setArgeSeqNo(tKmConsultRowsXxx.getArgeSeqno());

            //同一个记录下的字段查询
            List<ConsultDataVO.ConsultRowVO.ConsultRowsDataVO> listConsultRowData = new ArrayList<>();
            List<TKmConsultData> listConsultData = listConsultRowsData(tKmConsultRowsXxx);
            for(TKmConsultData tKmConsultData: listConsultData){
                //查询咨询表字段模板信息
                TKmConsultKeys tKmConsultKeys = keysMap.get(tKmConsultData.getColmId());
                if(tKmConsultKeys == null){
                    logger.error("模板字段不存在" + tKmConsultData.getColmId());
                    throw new GeneralException("114106");
                }

                //过滤

                ConsultDataVO.ConsultRowVO.ConsultRowsDataVO rowsDataVO = rowVO.createConsultRowData();
                rowsDataVO.setColmId(tKmConsultData.getColmId());
                rowsDataVO.setColmNm(tKmConsultKeys.getColmNm());
                rowsDataVO.setColmCode(tKmConsultKeys.getColmCode());
                rowsDataVO.setColmTypeCd(tKmConsultKeys.getColmTypeCd());
                rowsDataVO.setKeyVal(tKmConsultData.getKeyval());
                listConsultRowData.add(rowsDataVO);
            }


            for(Map.Entry<Long, TKmConsultKeys> entry: keysMap.entrySet()){
                boolean findFlag = false;
                for(ConsultDataVO.ConsultRowVO.ConsultRowsDataVO consultRowsDataVO: listConsultRowData){
                    if(entry.getKey().equals(consultRowsDataVO.getColmId())){
                        findFlag = true;
                    }
                }
                //插入空字段
                if(!findFlag){
                    ConsultDataVO.ConsultRowVO.ConsultRowsDataVO rowsDataVO = rowVO.createConsultRowData();
                    TKmConsultKeys tKmConsultKeys = entry.getValue();
                    rowsDataVO.setColmId(tKmConsultKeys.getColmId());
                    rowsDataVO.setColmNm(tKmConsultKeys.getColmNm());
                    rowsDataVO.setColmCode(tKmConsultKeys.getColmCode());
                    rowsDataVO.setColmTypeCd(tKmConsultKeys.getColmTypeCd());
                    rowsDataVO.setKeyVal(null);
                    listConsultRowData.add(rowsDataVO);
                }
            }



            rowVO.setListConsultRowsData(listConsultRowData);
            listConsultRow.add(rowVO);
        }

        List<Map> listConsultRowResult = changeRowToList(listConsultRow);
        //todo 这里查询出所有的记录，应该不会有问题吧，数据量大的话需要改写
        List<Map> afterFilter = listConsultRowResult.subList(searchVO.getStart(),listConsultRowResult.size()>searchVO.getStart()+searchVO.getLength()?searchVO.getStart()+searchVO.getLength():listConsultRowResult.size());
        consultDataVO.setListConsultRowResult(afterFilter);
        consultDataVO.setTotal(listConsultRowResult.size());
        return consultDataVO;
    }

    /**
     *
     * @param searchVO 咨询记录查询vo
     * @return 咨询表对象
     * @throws GeneralException
     */
/*    @Override
    public int buildQueryResult(ConsultSearchVO searchVO) throws GeneralException {
        ConsultDataVO consultDataVO = new ConsultDataVO();

        *//*Map<Long,InnerListBean> params = buildSearchParaMap(searchVO.getSearchParams());*//*
        TKmConsultApply tKmConsultApply = iKmConsultSV.getConsultContent(searchVO.getCnslId());
        consultDataVO.setCnslId(tKmConsultApply.getCnslId());
        consultDataVO.setCnslNm(tKmConsultApply.getCnslNm());
        consultDataVO.setTmpltId(tKmConsultApply.getTmpltId());

        Map<Long,TKmConsultKeys> keysMap = mapConsultKeysByTmpltId(tKmConsultApply.getTmpltId());
        //同一个咨询表应用下的记录查询
        List<ConsultDataVO.ConsultRowVO> listConsultRow = new ArrayList<>();
        List<TKmConsultRowsXxx> listTKmConsultRows = listConsultRows(tKmConsultApply);
        //加载咨询表记录
        for(TKmConsultRowsXxx tKmConsultRowsXxx: listTKmConsultRows){
            ConsultDataVO.ConsultRowVO rowVO = consultDataVO.createConsultRow();
            rowVO.setRecId(tKmConsultRowsXxx.getRecId());
            rowVO.setArgeSeqNo(tKmConsultRowsXxx.getArgeSeqno());

            //同一个记录下的字段查询
            List<ConsultDataVO.ConsultRowVO.ConsultRowsDataVO> listConsultRowData = new ArrayList<>();
            List<TKmConsultData> listConsultData = listConsultRowsData(tKmConsultRowsXxx);
            for(TKmConsultData tKmConsultData: listConsultData){
                //查询咨询表字段模板信息
                TKmConsultKeys tKmConsultKeys = keysMap.get(tKmConsultData.getColmId());
                if(tKmConsultKeys == null){
                    logger.error("模板字段不存在" + tKmConsultData.getColmId());
                    throw new GeneralException("114106");
                }

                //过滤

                ConsultDataVO.ConsultRowVO.ConsultRowsDataVO rowsDataVO = rowVO.createConsultRowData();
                rowsDataVO.setColmId(tKmConsultData.getColmId());
                rowsDataVO.setColmNm(tKmConsultKeys.getColmNm());
                rowsDataVO.setColmCode(tKmConsultKeys.getColmCode());
                rowsDataVO.setColmTypeCd(tKmConsultKeys.getColmTypeCd());
                rowsDataVO.setKeyVal(tKmConsultData.getKeyval());
                listConsultRowData.add(rowsDataVO);
            }


            for(Map.Entry<Long, TKmConsultKeys> entry: keysMap.entrySet()){
                boolean findFlag = false;
                for(ConsultDataVO.ConsultRowVO.ConsultRowsDataVO consultRowsDataVO: listConsultRowData){
                    if(entry.getKey().equals(consultRowsDataVO.getColmId())){
                        findFlag = true;
                    }
                }
                //插入空字段
                if(!findFlag){
                    ConsultDataVO.ConsultRowVO.ConsultRowsDataVO rowsDataVO = rowVO.createConsultRowData();
                    TKmConsultKeys tKmConsultKeys = entry.getValue();
                    rowsDataVO.setColmId(tKmConsultKeys.getColmId());
                    rowsDataVO.setColmNm(tKmConsultKeys.getColmNm());
                    rowsDataVO.setColmCode(tKmConsultKeys.getColmCode());
                    rowsDataVO.setColmTypeCd(tKmConsultKeys.getColmTypeCd());
                    rowsDataVO.setKeyVal(null);
                    listConsultRowData.add(rowsDataVO);
                }
            }



            rowVO.setListConsultRowsData(listConsultRowData);
            listConsultRow.add(rowVO);
        }

        List<Map> listConsultRowResult = changeRowToList(listConsultRow);
        //todo 这里查询出所有的记录，应该不会有问题吧，数据量大的话需要改写
        List<Map> afterFilter = listConsultRowResult.subList(searchVO.getStart(),listConsultRowResult.size()>searchVO.getStart()+searchVO.getLength()?searchVO.getStart()+searchVO.getLength():listConsultRowResult.size());

        HashMap<String,Object> params = new HashMap<>();
        params.put("paraList",afterFilter);
    }*/

    private Map<Long, InnerListBean> buildSearchParaMap(List<InnerListBean> paraList){
        HashMap<Long, InnerListBean> map = new HashMap<>();
        for(InnerListBean param: paraList){
            map.put(param.getColumnId(),param);
        }
        return map;
    }
    private Map<Long, TKmConsultKeys> mapConsultKeysByTmpltId(Long tmpltId){
        Map<Long, TKmConsultKeys> map = new HashMap();
        TKmConsultKeysExample example = new TKmConsultKeysExample();
        TKmConsultKeysExample.Criteria criteria = example.createCriteria();
        criteria.andTmpltIdEqualTo(tmpltId);
        for(TKmConsultKeys keys: tKmConsultKeysDAO.selectByExample(example)){

            map.put(keys.getColmId(),keys);
        }
        return map;
    }
    private List<Map> changeRowToList(List<ConsultDataVO.ConsultRowVO> listConsultRow){
        List<Map> listRowResult = new ArrayList<>();
        for(ConsultDataVO.ConsultRowVO rowVo: listConsultRow){
            HashMap mapTemp = new HashMap();
            for(ConsultDataVO.ConsultRowVO.ConsultRowsDataVO dataVo: rowVo.getListConsultRowsData()){
                mapTemp.put(dataVo.getColmCode(),dataVo.getKeyVal());
            }
            listRowResult.add(mapTemp);
        }
        return listRowResult;
    }
    private List<TKmConsultData> listConsultRowsData(TKmConsultRowsXxx tKmConsultRowsXxx) {
        TKmConsultDataExample example = new TKmConsultDataExample();
        TKmConsultDataExample.Criteria criteria = example.createCriteria();
        criteria.andCnslIdEqualTo(tKmConsultRowsXxx.getCnslId());
        criteria.andRecIdEqualTo(tKmConsultRowsXxx.getRecId());
        return tKmConsultDataDAO.selectByExample(example);
    }
    private List<TKmConsultRowsXxx> listConsultRows(TKmConsultApply tKmConsultApply) {
        TKmConsultRowsXxxExample example = new TKmConsultRowsXxxExample();
        TKmConsultRowsXxxExample.Criteria criteria = example.createCriteria();
        if(tKmConsultApply != null && tKmConsultApply.getCnslId() != null){
            criteria.andCnslIdEqualTo(tKmConsultApply.getCnslId());
        }
        example.setOrderByClause(" modf_time desc ");
        return tKmConsultRowsXxxDAO.selectByExample(example);
    }
    /**
     * 查询咨询表记录详细信息
     *
     * @param recId
     * @param regnId
     * @return
     */
    @Override
    public List<TKmConsultData> getConsultData(Long recId, String regnId) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("recId", recId);
        condition.put("tabNm", Constants.NGKM_CONSULT_PARAM.CONSULT_DATA_PREFIX + regnId);

        return tKmConsultDataDAO.getConsultData(condition);
    }

    /**
     * 获取记录id
     *
     * @param map
     * @return
     */
    @Override
    public List<Long> getRecIds(Map<String, Object> map) {
        return tKmConsultRowsXxxDAO.getRecIds(map);
    }

    /**
     * 校验数据的正确性
     *
     * @param consultData
     * @param tmpltId
     * @return
     */
    public String checkConsultData(Map<String, String> consultData, Long tmpltId, boolean batchFlag){
        //获取模板信息
        List<TKmConsultKeys> consultTmpltDetail = iKmConsultTmpltSV.listTKmConsultKeys(tmpltId);
        //遍历模板数据 校验必填和表达式
        List<String> required = new ArrayList<>();
        List<String> pattern = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> num = new ArrayList<>();

        for(TKmConsultKeys key : consultTmpltDetail){
            if(Constants.NGKM_CONSULT_ISREQUIRED.REQUIRED.equals(String.valueOf(key.getRequiredFlag()))  && !batchFlag){
                //字段必填  判断数据是否有值
                if(StringUtil.isEmpty(consultData.get(key.getColmId().toString()))){
                    required.add(key.getColmNm());
                    continue;
                }
            }
            //正则表达式校验
            if(StringUtil.isNotEmpty(key.getTsvldRegexp()) && StringUtil.isNotEmpty(consultData.get(key.getColmId().toString()))){
                if(!Pattern.matches(key.getTsvldRegexp(), consultData.get(key.getColmId().toString()))){
                    pattern.add(key.getColmNm());
                }
            }

/*            //日期类型转换成long类型进行存储
            if(Constants.NGKM_CONSULT_COLM_TYPE.DATE.equals(key.getColmTypeCd())){
                try{
                    Long aLong = String2DateUtil.dateString2Long(consultData.get(key.getColmId().toString()));
                    if(aLong == null){
                        continue;
                    }
                    consultData.put(key.getColmId().toString(), aLong.toString());
                }catch(Exception e){
                    LOGGER.error("转换出错", e);
                    //throw new GeneralException("114114");
                    date.add(key.getColmNm());
                }
            }*/
            //数字类型校验
            if(Constants.NGKM_CONSULT_COLM_TYPE.NUM.equals(key.getColmTypeCd())){
                try{
                    String aLong = consultData.get(key.getColmId().toString());
                    if(aLong == null || "".equals(aLong)){
                        continue;
                    }
                    Long.parseLong(aLong);
                }catch(Exception e){
                    LOGGER.error("转换出错", e);
                    num.add(key.getColmNm());
                }
            }
        }
        //如果required 和pattern都为空  则校验通过 否则失败返回异常信息
        StringBuilder sb = new StringBuilder();
        if(!required.isEmpty()){
            sb.append("必填原子").append(required.toString()).append("不能为空  ");
        }

        if(!pattern.isEmpty()){
            sb.append("下列原子表达式校验失败:").append(pattern.toString());
        }

        if(!date.isEmpty()){
            sb.append("以下日期类型原子填写错误: ").append(date.toString());
        }

        if(!num.isEmpty()){
            sb.append("以下数字类型原子填写错误: ").append(num.toString());
        }

        return sb.toString();
    }

    /**
     * 校验记录是否存在
     *
     * @param recIds
     * @param regnId
     * @return
     */
    @Override
    public List<Long> checkDelete(String recIds, String regnId) {
        String tabNm = Constants.NGKM_CONSULT_PARAM.CONSULT_ROW_PREFIX + regnId;
        String[] ids = recIds.split(",");
        Map<String, Object> map = new HashMap<>(2);
        map.put("tabNm", tabNm);
        List<Long> list = new ArrayList<>();
        for(String str: ids){
            map.put("recId", Long.parseLong(str));

            TKmConsultRowsXxx recMessage = tKmConsultRowsXxxDAO.getRecMessage(map);
            if(recMessage == null){
                list.add(Long.parseLong(str));
            }
        }
        return list;
    }

    /**
     * 插入咨询表详细信息
     *
     * @param data
     */
    private Long saveConsultRow(Map<String, Object> data, boolean batchFlag) throws GeneralException{
        //咨询表记录数据
        Map<String, String> consultData = (Map<String, String>)data.get("data");
        String regnId = "000";
        String staffCode = (String)data.get("staffCode");
        //咨询表id
        Long cnslId = (Long)data.get("cnslId");
        //要修改的行记录id(逗号分隔)
        String recIds = (String)data.get("recIds");
        String tenantId = (String)data.get("tenantId");

        String rowTabNm = Constants.NGKM_CONSULT_PARAM.CONSULT_ROW_PREFIX + regnId;
        String dataTabNm = Constants.NGKM_CONSULT_PARAM.CONSULT_DATA_PREFIX + regnId;
        if(StringUtil.isNotEmpty(recIds)){
            //截取cnslId
            String[] ids = recIds.split(",");
            Map<String, Object> map = new HashMap<>();
            map.put("tabNm", rowTabNm);
            map.put("modfTime", new Timestamp(System.currentTimeMillis()));
            map.put("opPrsnId", staffCode);
            map.put("recIds", ids);
            //更新行记录表
            tKmConsultRowsXxxDAO.updateConsultRows(map);
            //获取数据记录id并执行删除操作
            map.put("tabNm", dataTabNm);
            //批量更新  只删除要保存的
            if(batchFlag){
                Set<Map.Entry<String, String>> entries = consultData.entrySet();
                List<String> colmIds = new ArrayList<>();
                for(Map.Entry<String, String> entry : entries){
                    colmIds.add(entry.getKey());
                }

                map.put("colmIds", colmIds);
            }
            List<Long> cnttIds = tKmConsultDataDAO.getCnttIds(map);
            map.put("cnttIds", cnttIds);
            tKmConsultDataDAO.deleteMult(map);

            for(String id : ids){
                //重新插入列记录
                saveConsultData(consultData, dataTabNm, Long.parseLong(id), cnslId, tenantId, staffCode);
            }

            if(batchFlag){
                return -1L;
            }else{
                return Long.valueOf(recIds);
            }

        }else{
            //新增
            TKmConsultRowsXxx kmConsultRowsXxx = new TKmConsultRowsXxx();
            Long recId = Long.valueOf(BasicUtil.getKeysInstant().getSequence(rowTabNm));

            kmConsultRowsXxx.setRecId(recId);
            kmConsultRowsXxx.setCnslId(cnslId);
            kmConsultRowsXxx.setOpPrsnId(staffCode);
            kmConsultRowsXxx.setTenantId(tenantId);
            kmConsultRowsXxx.setCrtTime(new Timestamp(System.currentTimeMillis()));
            kmConsultRowsXxx.setModfTime(new Timestamp(System.currentTimeMillis()));
            kmConsultRowsXxx.setTabNm(rowTabNm);
            //保存记录
            tKmConsultRowsXxxDAO.saveConsultRows(kmConsultRowsXxx);
            saveConsultData(consultData, dataTabNm, recId, cnslId, tenantId, staffCode);

            return recId;
        }
    }

    /**
     * 插入咨询表记录表
     *
     * @param consultData
     * @param dataTabNm
     * @param recId
     * @param cnslId
     * @param tenantId
     * @param staffCode
     * @throws GeneralException
     */
    private void saveConsultData(Map<String, String> consultData, String dataTabNm, Long recId, Long cnslId, String tenantId, String staffCode) throws GeneralException{
        List<TKmConsultData> list = new ArrayList<>();
        TKmConsultData kmConsultData;
        for(Map.Entry<String, String> entry : consultData.entrySet()){
            kmConsultData = new TKmConsultData();
            if(StringUtil.isNotEmpty(entry.getValue())){
                kmConsultData.setKeyval(entry.getValue());
            }else{
                continue;
            }
            kmConsultData.setCnslId(cnslId);
            kmConsultData.setOpPrsnId(staffCode);
            kmConsultData.setRecId(recId);
            kmConsultData.setTenantId(tenantId);
            kmConsultData.setColmId(Long.parseLong(entry.getKey()));
            Long cnttId = Long.valueOf(BasicUtil.getKeysInstant().getSequence(dataTabNm));
            kmConsultData.setCnttId(cnttId);

            list.add(kmConsultData);
        }
        //保存记录数据
        Map<String, Object> map = new HashMap<>();
        map.put("tabNm", dataTabNm);
        map.put("consultData", list);
        tKmConsultDataDAO.saveDataMult(map);
    }

}
