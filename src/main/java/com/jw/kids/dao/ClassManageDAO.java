package com.jw.kids.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
public interface ClassManageDAO {

    /**
     *  根据班级id查询教师
     * @param classId
     * @return
     */
    List<Map> listByClassId(Long classId);

    List<Map> listClassLog(HashMap params);

    List<Map> listLogDetail(Map params);
}
