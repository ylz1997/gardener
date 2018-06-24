package com.jw.consult.controller.consult;


import com.jw.base.BaseFormateter;
import com.jw.base.GeneralException;
import com.jw.consult.bean.TKmConsultCatl;
import com.jw.consult.controller.aop.ResultFormatter;
import com.jw.consult.service.IKmConsultCatlSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultCatl")
public class KmConsultCatlController {
    @Autowired
    private IKmConsultCatlSV kmConsultCatlSVImpl;
/*    @Autowired
    private UserController userController;*/


    @RequestMapping(path = "getConsultCatl")
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public List<TKmConsultCatl> getConsultCatl(Long catlId){
        //UserInfo userInfo = userController.getUserInfoSession();
        TKmConsultCatl tKmConsultCatl = new TKmConsultCatl();
        tKmConsultCatl.setSuprCatlId(catlId);
        tKmConsultCatl.setTenantId("-1");
        tKmConsultCatl.setRegnId("0");
        List<TKmConsultCatl> resultList = new ArrayList();
        if(catlId == 0L){
            List<TKmConsultCatl> childrenList;
            TKmConsultCatl sup = new TKmConsultCatl();
            sup.setSuprCatlId(-1L);
            sup.setCatlId(0L);
            sup.setCatlNm("模板目录");
            sup.setArgeSeqno(Short.parseShort("1"));
            childrenList = kmConsultCatlSVImpl.getConsultCatl(tKmConsultCatl);
            if(childrenList != null && !childrenList.isEmpty()){
                sup.setIsParent(true);
            }
            for(TKmConsultCatl children : childrenList ){
                TKmConsultCatl catl = new TKmConsultCatl();
                catl.setSuprCatlId(children.getCatlId());
                catl.setTenantId("-1");
                catl.setRegnId("0");
                List list = kmConsultCatlSVImpl.getConsultCatl(catl);
                if(!list.isEmpty()){
                    children.setIsParent(true);
                }
            }
            sup.setChildren(childrenList);
            resultList.add(sup);
        }else{
            resultList = kmConsultCatlSVImpl.getConsultCatl(tKmConsultCatl);
            for(TKmConsultCatl catlList : resultList ) {
                TKmConsultCatl catl = new TKmConsultCatl();
                catl.setSuprCatlId(catlList.getCatlId());
                catl.setTenantId("-1");
                catl.setRegnId("0");
                List list = kmConsultCatlSVImpl.getConsultCatl(catl);
                if (!list.isEmpty()) {
                    catlList.setIsParent(true);
                }
            }
        }
        return resultList;
    }

    @RequestMapping(path = "saveConsultCatl")
    //@ResultFormatter(formatterClass = BaseFormateter.class)
    public Map saveConsultCatl(TKmConsultCatl consultCatl) throws GeneralException {
        consultCatl.setOpPrsnId("-1");
        consultCatl.setRegnId("0");
        consultCatl.setTenantId("-1");
        Map map = kmConsultCatlSVImpl.saveConsultCatl(consultCatl);
        return map;
    }

    @RequestMapping(path = "updateConsultCatl")
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void updateCatalog(TKmConsultCatl consultCatl) throws GeneralException {
        consultCatl.setOpPrsnId("-1");
        consultCatl.setModfTime(new Timestamp(System.currentTimeMillis()));
        kmConsultCatlSVImpl.updateCatalog(consultCatl);
    }

    @RequestMapping(path = "deleteConsultCatl")
    @ResultFormatter(formatterClass = BaseFormateter.class)
    public void deleteConsultCatl(TKmConsultCatl consultCatl) throws GeneralException {
        consultCatl.setOpPrsnId("-1");
        consultCatl.setModfTime(new Timestamp(System.currentTimeMillis()));
        consultCatl.setCatlTypeCd("1");
        kmConsultCatlSVImpl.updateCatalog(consultCatl);
    }
}
