package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TClassLogDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
public interface KidsClassLogDetailSV {
    TClassLogDetail add(TClassLogDetail tClassLogDetail) throws GeneralException;

    HashMap listLogDetail(TClassLogDetail tClassLogDetail, Integer start, Integer end);

    Integer total(TClassLogDetail tClassLogDetail);
}
