package com.jw.kids.controller.aop;

/**
 * @author jw
 * @desc
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 学生操作记录注解
 * @author jw
 * 2018-7-13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KidsLog{
    String[] operType() default "null opType";
}
