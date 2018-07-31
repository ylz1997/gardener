package com.jw.kids.controller.intercepter;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 知识日志切面类
 * @author jw
 * 2018-2-11
 */
@Aspect
@Component
public class ClassLogAspect {
/*    @Autowired
    private KnwlgLogController knwlgLogController;
    *//**
     * 定义切面范围
     *//*
    @Pointcut("@annotation(com.cmos.ngkm.web.aop.KnwlgLog)")
    public void pointcut() {
    }

    @AfterReturning (value = "pointcut()" , returning = "result")
    public void executeSpringRestfulService(JoinPoint joinPoint, String result) throws Throwable{
        ClassLog classLogAnno = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ClassLog.class);
        //String[] opTypes = classLogAnno.operType();

        Map resultMap = (Map) JsonUtil.convertJson2Object(result, Map.class);
        TClassLog tClassLog = (TClassLog) resultMap.get("classLog");
        TKmOperateLog operateLog = (TKmOperateLog)resultMap.get("operateLog");
*//*        for(String opType:opTypes){
            knwlgLogController.traceKnwlgLog(docEdit , opType, operateLog);
        }*//*
    }*/
}
