package com.comeup.spring.common.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Component
public class B {

    private static final Logger LOGGER = LoggerFactory.getLogger(B.class);

    @Autowired
    private A a;




}
