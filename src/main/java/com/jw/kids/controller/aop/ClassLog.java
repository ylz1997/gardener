package com.jw.kids.controller.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 课堂日志注解
 * @author jw
 * 2018-7-13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassLog{
    String[] operType() default "null opType";
}
