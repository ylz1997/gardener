package com.jw.consult.controller.aop;

import com.jw.base.ValueFormatter;

import java.lang.annotation.*;

/**
 * @author jw
 * @desc
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @Documented
    public @interface ResultFormatter {
        Class<? extends ValueFormatter> formatterClass();
    }
