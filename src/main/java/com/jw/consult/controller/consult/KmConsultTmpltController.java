package com.jw.consult.controller.consult;

import com.jw.base.BaseFormateter;
import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.base.JsonUtil;
import com.jw.consult.bean.TKmConsultKeys;
import com.jw.consult.bean.TKmConsultTmplt;
import com.jw.consult.controller.aop.ResultFormatter;
import com.jw.consult.service.IKmConsultTmpltSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 咨询表模板操作入口
 *
 * @author jw 76902  2018-02-28
 */
@RestController
@RequestMapping("/consultTmplt")
public class KmConsultTmpltController {

    @Autowired
    private IKmConsultTmpltSV iKmConsultTmpltSV;
/*    @Autowired
    private IKMDistrictConfigCacheSV kmDistrictCacheSV;*/
/*    @Autowired
    private UserComponent userController;*/
    private static final Logger logger = LoggerFactory.getLogger(KmConsultTmpltController.class);
    /**
     * 查询模板列表
     * @param tKmConsultTmplt
     * @return
     */
    @RequestMapping(path = "getConsultList")
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public String getConsultTmpltList(final Integer limit, final Integer page, final TKmConsultTmplt tKmConsultTmplt){
        List<TKmConsultTmplt> list = iKmConsultTmpltSV.getConsultTmpltList(limit, page, tKmConsultTmplt);
        int count = iKmConsultTmpltSV.getConsultTmpltListCount(tKmConsultTmplt);
        HashMap result = new HashMap();
        result.put("beans",list);
        result.put("total",count);
        return JsonUtil.convertObject2Json(result);
    }


    /**
     * 查询模板列表
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getTmpltList",method = RequestMethod.GET)
    public String getConsultTmpltList(TKmConsultTmplt tKmConsultTmplt, int start, int limit){
        HashMap result = new HashMap();
        int size = iKmConsultTmpltSV.getConsultTmpltList(tKmConsultTmplt).size();
        List list = iKmConsultTmpltSV.getConsultTmpltList(tKmConsultTmplt, start, limit);
        result.put("beans",list);
        result.put("total",size);
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 查询模板列表
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getTmpltListNoPage",method = RequestMethod.GET)
    public String getConsultTmpltListNoPage(TKmConsultTmplt tKmConsultTmplt, int draw){
        HashMap result = new HashMap();
        //todo 写死地区
        tKmConsultTmplt.setAuthRegn("771");
        List list = iKmConsultTmpltSV.getConsultTmpltList(tKmConsultTmplt);

        result.put("data",list);
        result.put("recordTotal",list.size());
        result.put("recordsFiltered",list.size());
        result.put("draw", draw);
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 根据模板id查询咨询表模板原子
     *
     * @param tmpltId
     * @return
     * @throws GeneralException
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getAllKeysByCache",method = RequestMethod.GET)
    public String getAllKeysByCache(Long tmpltId) throws GeneralException{
        if(tmpltId == null){
            logger.error("咨询表模板id为空");
            throw new GeneralException("114105");
        }
        List<TKmConsultKeys> allKeys = iKmConsultTmpltSV.getAllKeys(tmpltId);
        HashMap result = new HashMap();
        result.put("beans",allKeys);

        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 查询模板
     */
    //@ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getTmplt/{tmpltId}",method = RequestMethod.GET)
    public String getConsultTmplt(@PathVariable String tmpltId){
        HashMap<String,Object> result = new HashMap();
        Long lTmpltId = Long.parseLong(tmpltId);
        result.put("object",iKmConsultTmpltSV.getConsultTmplt(lTmpltId));
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 查询模板列 不带分页
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getTmpltColumnNoPage",method = RequestMethod.GET)
    public String listTKmConsultKeys(@RequestParam String tmpltId){

        HashMap result = new HashMap();
        Long lTmpltId = Long.parseLong(tmpltId);
        List list = iKmConsultTmpltSV.listTKmConsultKeys(lTmpltId);
        result.put("beans",list);
        result.put("total",list.size());
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 查询单个模板列
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getTmpltColumn",method = RequestMethod.GET)
    public String getConsultTmpltColumn(@RequestParam String colmId){
        HashMap result = new HashMap();
        Long lColmId = Long.parseLong(colmId);
        result.put("object",(iKmConsultTmpltSV.getConsultTmpltColumn(lColmId)));
        return JsonUtil.convertObject2Json(result);
    }

    /**
     * 查询模板列列表
     */
    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/getTmpltColumnList",method = RequestMethod.GET)
    public String listTKmConsultKeys(@RequestParam String tmpltId, @RequestParam int start, @RequestParam int limit){

        HashMap result = new HashMap();
        Long lTmpltId = Long.parseLong(tmpltId);
        int size = iKmConsultTmpltSV.listTKmConsultKeys(lTmpltId).size();
        List list = iKmConsultTmpltSV.listTKmConsultKeys(lTmpltId, start, limit);
        result.put("beans",list);
        result.put("totoal",size);
        return JsonUtil.convertObject2Json(result);
    }

/*    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/transDistrictNm/{districtIds}", method = RequestMethod.GET)
    public String getDistrictNms(@PathVariable String districtIds){
        if(StringUtil.isEmpty(districtIds)){
            HashMap result = new HashMap();
            result.put("returnCode","-1");
            result.put("returnMessage","districtIds is null");
            return result.toString();
        }

        StringBuilder sb = new StringBuilder();
        for(String districtId:districtIds.split(",")){
            TKmDistrictConfig tKmDistrictConfig = kmDistrictCacheSV.getTKmDistrictConfigByRegnId(districtId);
            if(tKmDistrictConfig != null){
                sb.append(tKmDistrictConfig.getRegnNm());
                sb.append("，");
            }
            else{
                sb.append(districtId);
                sb.append("，");
            }
        }
        Result result = new Result();
        result.setObject(sb.substring(0,sb.length()-1));
        return result;
    }*/

    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/saveOrUpdateKeys", method = RequestMethod.POST)
    public String saveOrUpdateConsultTmpltColm(TKmConsultKeys tKmConsultKeys) throws GeneralException {
/*
        UserInfo userInfo = userController.getUserInfoSession();
        tKmConsultKeys.setOpPrsnId(userInfo.getStaffCode());
        tKmConsultKeys.setModfTime(DateUtil.getCurrontTime());
*/
        HashMap result = new HashMap();
        result.put("returnCode","0");
        result.put("returnMessage",iKmConsultTmpltSV.saveOrUpdateConsultTmpltColm(tKmConsultKeys));

        return JsonUtil.convertObject2Json(result);
    }

    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/saveTmplt", method = RequestMethod.POST)
    public String saveConsultTmplt(TKmConsultTmplt tKmConsultTmplt) throws GeneralException {
        HashMap result = new HashMap();
        result.put("returnCode","0");
        result.put("returnMessage",iKmConsultTmpltSV.saveConsultTmplt(tKmConsultTmplt));
        return JsonUtil.convertObject2Json(result);
    }

    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/deleteTmpltColm", method = RequestMethod.POST)
    public String deleteConsultTmplt(@RequestParam String colmId) throws GeneralException {
        HashMap result = new HashMap();
        result.put("returnCode","0");
        result.put("returnMessage",iKmConsultTmpltSV.deleteConsultKeys(colmId));
        return JsonUtil.convertObject2Json(result);
    }

    @ResultFormatter(formatterClass = BaseFormateter.class)
    @RequestMapping(value = "/modifyTmpltColmArgeSeqno", method = RequestMethod.POST)
    public String deleteConsultTmplt(TKmConsultKeys tKmConsultKeys) throws GeneralException {
        iKmConsultTmpltSV.modifyArgeSeqno(tKmConsultKeys);
        HashMap result = new HashMap();
        result.put("returnCode","0");
        result.put("returnMessage","success");
        return JsonUtil.convertObject2Json(result);
        //tKmConsultKeys.setOpPrsnId(userController.getUserInfoSession().getStaffCode());
    }



    /**
     * 新增咨询表模板
     * @param tKmConsultTmplt
     * @return
     */
    @PostMapping(value = "/saveConsultTmpltInfo")
    public String saveTmpltInfo(TKmConsultTmplt tKmConsultTmplt) throws GeneralException {
        String result = new String();
        //正常保存
        String id;
        try {
            id = BasicUtil.getKeysInstant().getSequence("t_km_consult_tmplt");
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new GeneralException("生成主键错误");
        }
        tKmConsultTmplt.setTmpltId(Long.parseLong(id));
      /*  tKmConsultTmplt.setOpPrsnId(userController.getUserInfoSession().getStaffCode());
        tKmConsultTmplt.setTenantId(userController.getUserInfoSession().getTenantId());
*/
        Boolean isRight;
        try {
            isRight = iKmConsultTmpltSV.save(tKmConsultTmplt);
        } catch (GeneralException e) {
            throw new GeneralException(e.getErrorCode(),e);
        }
        if (isRight) {
            result = id;
        }
        return JsonUtil.convertObject2Json(result);
    }
    /**
     * 咨询表模板添加相似
     * @param tKmConsultTmplt
     * @return
     */
    @PostMapping(value = "/extendConsultTmpltInfo")
    public String extendTmpltInfo(TKmConsultTmplt tKmConsultTmplt) throws GeneralException {
        String result = new String();
        if (tKmConsultTmplt.getTmpltId() != null) {
            //扩展模板
            String newTmpletId = BasicUtil.getKeysInstant().getSequence("t_km_consult_tmplt");
        /*    tKmConsultTmplt.setOpPrsnId(userController.getUserInfoSession().getStaffCode());
            tKmConsultTmplt.setTenantId(userController.getUserInfoSession().getTenantId());
*/
            boolean extendTrue;
            try {
                extendTrue = iKmConsultTmpltSV.extendTmpltInfo(tKmConsultTmplt, newTmpletId);
            } catch (GeneralException e) {
                logger.info(e.getMessage(),e);
                throw new GeneralException(e.getErrorCode(),e);
            }
            if (extendTrue) {
                result = newTmpletId;
            }
        }
        return JsonUtil.convertObject2Json(result);
    }
    /**
     * 修改咨询表模板
     * @param tKmConsultTmplt
     * @return
     */
    @PostMapping(value = "/updateConsultTmpltInfo")
    public String updateTmpltInfo(TKmConsultTmplt tKmConsultTmplt) throws GeneralException{
        String result = new String();
        Boolean isRight ;
        try {
            isRight = iKmConsultTmpltSV.updateByPrimaryKeySelective(tKmConsultTmplt);
        }catch (GeneralException e) {
            logger.info(e.getMessage(),e);
            throw new GeneralException(e.getErrorCode(),e);
        }

        if (isRight) {
            result = String.valueOf(tKmConsultTmplt.getTmpltId());
        }
        return JsonUtil.convertObject2Json(result);
    }


    @RequestMapping(value = "/deleteConsultTmpltInfo", method = RequestMethod.POST)
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void deleteTmpltInfo(Long tmpltId) throws GeneralException{
        logger.info("要删除的咨询表模板id是："+tmpltId);
        try {
            iKmConsultTmpltSV.deleteByPrimaryKey(tmpltId);
        } catch (GeneralException e) {
            throw new GeneralException(e.getErrorCode(),e);
        }

    }
}
