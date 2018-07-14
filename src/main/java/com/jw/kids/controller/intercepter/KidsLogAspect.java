package com.jw.kids.controller.intercepter;

import com.jw.base.DateUtil;
import com.jw.base.JsonUtil;
import com.jw.kids.bean.TClassPackage;
import com.jw.kids.bean.TKids;
import com.jw.kids.bean.TKidsVO;
import com.jw.kids.controller.KidsLogController;
import com.jw.kids.controller.aop.KidsLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author jw
 * @desc
 */
@Aspect
@Component
public class KidsLogAspect {
    @Autowired
    private KidsLogController kidsLogController;

    @Pointcut("@annotation(com.jw.kids.controller.aop.KidsLog)")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()" , returning = "result")
    public void executeSpringRestfulService(JoinPoint joinPoint, String result) throws Throwable{
        KidsLog kidsAnno = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(KidsLog.class);
        String[] opTypes = kidsAnno.operType();

        Map resultMap = (Map) JsonUtil.convertJson2Object(result, Map.class);
        Map tkidsMap = (Map) resultMap.get("tKids");
        TKids tKids = new TKids();
        tkidsMap.put("crtTime", DateUtil.convertDateStringToTimestamp((String)tkidsMap.get("crtTime"), "yyyy-MM-dd HH:mm:ss"));
        tkidsMap.put("modfTime", DateUtil.convertDateStringToTimestamp((String)tkidsMap.get("modfTime"), "yyyy-MM-dd HH:mm:ss"));
        BeanUtils.populate(tKids, tkidsMap);
        for(String opType:opTypes){
            if("charge".equals(opType)){
                Map classPackage = (Map) resultMap.get("tClassPackage");
                kidsLogController.add(opType, tKids);
                tKids.setAmount((Integer)classPackage.get("amount"));
            }else{
                kidsLogController.add(opType, tKids);
            }
        }
    }
}
