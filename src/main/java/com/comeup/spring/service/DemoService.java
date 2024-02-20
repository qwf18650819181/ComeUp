package com.comeup.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
