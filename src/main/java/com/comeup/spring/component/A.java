package com.comeup.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Named
public class A implements InitializingBean {

    @Autowired
    private B b;
    @Autowired
    Environment environment;
    @Autowired
    ApplicationContext applicationContext;


    public String getProperty(String name) {
        return environment.getProperty(name);
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(A.class);

    @PostConstruct
    public void test() {
        LOGGER.info("a start 1");
        System.out.println("a");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("a start 2");
        System.out.println("a");
    }

    /**
     * 产生循环依赖问题
     */
//    @Async
//    public void doSome() {
//        LOGGER.info("a doSome ....");
//    }


}
