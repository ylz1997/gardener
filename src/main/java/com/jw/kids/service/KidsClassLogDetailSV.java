package com.jw.kids.service;

import com.jw.kids.bean.TClassLogDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
public interface KidsClassLogDetailSV {

    HashMap listLogDetail(TClassLogDetail tClassLogDetail, Integer start, Integer end);

    Integer total(TClassLogDetail tClassLogDetail);
}
