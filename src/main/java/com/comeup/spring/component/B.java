package com.comeup.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

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
