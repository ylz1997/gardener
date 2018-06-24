package com.jw.base;

/**
 * @author jw
 * @desc
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class AppContext implements ApplicationContextAware {
    private static ApplicationContext _applicationContext = null;

    public AppContext() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        _applicationContext = applicationContext;
    }

    public static final ApplicationContext context() {
        return _applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T) _applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> type) {
        return _applicationContext.getBean(type);
    }

    public static <T> Map<String, T> getBeans(Class<T> type) {
        return _applicationContext.getBeansOfType(type);
    }

    public static void doInjection(Object obj) {
    }

    public static boolean hasBean(String beanName) {
        return _applicationContext.containsBean(beanName);
    }
}
