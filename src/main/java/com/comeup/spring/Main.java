package com.comeup.spring;

import com.comeup.spring.aspect.AComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Configuration
@ComponentScan("com.comeup.spring")
@EnableAsync
@EnableAspectJAutoProxy
@Slf4j
public class Main {

//    @Bean
//    public MyFactoryBean myFactoryBean() {
//        return new MyFactoryBean();
//    }



    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        log.info("logger");
        AComponent a = (AComponent) applicationContext.getBean("AComponent");
        a.test();

        log.info("====== 局部轮询反转链表 ======");

//        A bean = applicationContext.getBean(A.class);
//        System.out.println(bean);
//        System.out.println(bean.getProperty("user.dir"));
//        System.out.println(bean.getProperty("SESSIONNAME"));
    }



}
