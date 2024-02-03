package com.comeup.spring.initializiingbean;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 *
 * PostConstruct 是j2ee规范
 * InitializingBean 是spring扩展
 */
@Named
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyInitializingBean afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("MyInitializingBean PostConstruct");
    }
}
