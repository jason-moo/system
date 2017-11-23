package com.crm.web.interceptor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by jason_moo on 2017/10/27.
 */
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanFactory.applicationContext = applicationContext;
    }
}
