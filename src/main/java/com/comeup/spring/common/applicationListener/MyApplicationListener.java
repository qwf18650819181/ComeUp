package com.comeup.spring.common.applicationListener;

import org.springframework.context.ApplicationListener;

import javax.inject.Named;

/**
 * @auth: qwf
 * @date: 2024年1月24日 0024
 * @description:
 */
@Named
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("MyApplicationListener event");
    }
}
