package com.comeup.spring.createbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Configuration
@ComponentScan("com.comeup.spring.createbean")
@Slf4j
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        log.info("logger");
        MyBean myBean = applicationContext.getBean(MyBean.class);
        myBean.myFactoryBean();
    }



}
