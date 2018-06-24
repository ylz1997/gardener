package com.jw.consult.service;


import com.jw.base.GeneralException;
import com.jw.consult.bean.TKmConsultCatl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IKmConsultCatlSV {

    /**
     * 获取目录信息
     * @param tKmConsultCatl
     * @return
     */
    List<TKmConsultCatl> getConsultCatl(TKmConsultCatl tKmConsultCatl);

    /**
     * 新增
     * @param tKmConsultCatl
     * @return
     */
    Map saveConsultCatl(TKmConsultCatl tKmConsultCatl) throws GeneralException;

    /**
     * 修改
     * @param tKmConsultCatl
     * @ 
     */
    void updateCatalog(TKmConsultCatl tKmConsultCatl) throws GeneralException;


}
