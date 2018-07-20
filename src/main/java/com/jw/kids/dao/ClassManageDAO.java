package com.jw.kids.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
public interface ClassManageDAO {
    List<Map> listByClassId(Long classId);
}
