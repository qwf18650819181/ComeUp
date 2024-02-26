package com.comeup.spring.createbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @auth: qwf
 * @date: 2024年2月26日 0026
 * @description:
 */
@Component
@Slf4j
public class MyBean {

    public void myFactoryBean() {
        log.info("MyBean myFactoryBean do");
    }
}
