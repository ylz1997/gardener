package com.jw.consult.service.impl;

import com.jw.base.BasicUtil;
import com.jw.base.GeneralException;
import com.jw.consult.bean.TKmConsultCatl;
import com.jw.consult.dao.TKmConsultCatlDAO;
import com.jw.consult.service.IKmConsultCatlSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KmConsultCatlSVImpl implements IKmConsultCatlSV {
    @Autowired
    private TKmConsultCatlDAO tKmConsultCatlDao;

    /**
     * 获取目录信息
     * @param tKmConsultCatl
     * @return
     */
    @Override
    public List<TKmConsultCatl> getConsultCatl(TKmConsultCatl tKmConsultCatl) {
        return tKmConsultCatlDao.getConsultCatl(tKmConsultCatl);
    }

    @Override
    public Map saveConsultCatl(TKmConsultCatl tKmConsultCatl) throws GeneralException {
        String catlId = BasicUtil.getKeysInstant().getSequence("t_km_consult_catl");
        TKmConsultCatl consultCatl = new TKmConsultCatl();
        consultCatl.setSuprCatlId(tKmConsultCatl.getSuprCatlId());
        consultCatl.setTenantId(tKmConsultCatl.getTenantId());
        consultCatl.setRegnId(tKmConsultCatl.getRegnId());
        String seqnoStr = tKmConsultCatlDao.getConsultCatlMaxSeqno(consultCatl);
        Short seqno = seqnoStr == null ? 0 : Short.parseShort(seqnoStr);
        seqno = (short)(seqno + 1);
        tKmConsultCatl.setCatlId(Long.parseLong(catlId));
        tKmConsultCatl.setArgeSeqno(seqno);
        tKmConsultCatl.setCatlTypeCd("0");
        tKmConsultCatl.setCrtTime(new Timestamp(System.currentTimeMillis()));
        tKmConsultCatl.setModfTime(new Timestamp(System.currentTimeMillis()));
        int count = tKmConsultCatlDao.saveSelective(tKmConsultCatl);
        if(count != 1){
            throw new GeneralException("保存失败!");
        }
        Map map = new HashMap();
        map.put("catlId",catlId);
        map.put("seqno",seqno);
        return map;
    }

    public void updateCatalog(TKmConsultCatl tKmConsultCatl) throws GeneralException {
        int count;
        if(tKmConsultCatl.getTargetCatlId() == null){//修改
            count = tKmConsultCatlDao.updateByPrimaryKeySelective(tKmConsultCatl);
            if(count != 1){
                throw new GeneralException("操作失败!");
            }
        }else{//移动
            TKmConsultCatl targetConsultCatl = new TKmConsultCatl();
            //交换序号
            Short seqno = tKmConsultCatl.getArgeSeqno();
            tKmConsultCatl.setArgeSeqno(tKmConsultCatl.getTargetSeqno());
            targetConsultCatl.setCatlId(tKmConsultCatl.getTargetCatlId());
            targetConsultCatl.setArgeSeqno(seqno);
            targetConsultCatl.setOpPrsnId(tKmConsultCatl.getOpPrsnId());
            targetConsultCatl.setModfTime(tKmConsultCatl.getModfTime());
            count = tKmConsultCatlDao.updateByPrimaryKeySelective(tKmConsultCatl);
            if(count != 1){
                throw new GeneralException("操作失败!");
            }
            count = tKmConsultCatlDao.updateByPrimaryKeySelective(targetConsultCatl);
            if(count != 1){
                throw new GeneralException("操作失败!");
            }
        }
    }

}
