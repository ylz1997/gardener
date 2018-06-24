package com.jw.kids.service;

import com.jw.base.GeneralException;
import com.jw.kids.bean.TKids;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author jw
 * @desc
 */
public interface KidsStudentService {
    TKids addStudent(TKids tKids) throws GeneralException;
}
