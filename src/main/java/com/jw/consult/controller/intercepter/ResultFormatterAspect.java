package com.jw.consult.controller.intercepter;

import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author lye
 * @data 2017/11/8.17:14
 */
@Aspect
@Component
public class ResultFormatterAspect {
    static final String[] MAPPING_CLASSES = { "org.springframework.web.bind.annotation.RequestMapping", "org.springframework.web.bind.annotation.DeleteMapping", "org.springframework.web.bind.annotation.GetMapping", "org.springframework.web.bind.annotation.PostMapping", "org.springframework.web.bind.annotation.PutMapping", "org.springframework.web.bind.annotation.PatchMapping" };

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultFormatterAspect.class);

    /**
     * 定义切面范围
     */
    @Pointcut("@annotation(com.jw.consult.controller.aop.ResultFormatter)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object executeSpringRestfulService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if ((proceedingJoinPoint instanceof MethodInvocationProceedingJoinPoint)) {
            MethodInvocationProceedingJoinPoint methodJoinPoint = (MethodInvocationProceedingJoinPoint)proceedingJoinPoint;
            for (String className : MAPPING_CLASSES)
                try {
                    Annotation annotation = ((MethodSignature)methodJoinPoint.getSignature()).getMethod().getAnnotation((Class<Annotation>) Class.forName(className));
                    if (annotation != null){
                        /*return TraceManager.getInstance().performTrace(TraceContext.Type.CONTROLLER, new Object[] { proceedingJoinPoint });*/
                        //todo 这里实现注解命中逻辑
                    }
                } catch (ClassNotFoundException localClassNotFoundException){
                    LOGGER.error("class未找到", localClassNotFoundException);
                }
        }
        return proceedingJoinPoint.proceed();
    }
}
