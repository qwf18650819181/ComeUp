package com.comeup.spring.common.service;

/**
 * @auth: qwf
 * @date: 2024年2月20日 0020
 * @description:
 */
public interface DemoService {

    default void test() {
        System.out.println("run run run ...");
    }

}
