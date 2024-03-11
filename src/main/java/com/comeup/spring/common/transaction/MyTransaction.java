package com.comeup.spring.common.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月11日 0011
 * @version: 1.0
 */
@Component
public class MyTransaction {

    @Transactional
    public void test(){
        System.out.println("1123");
    }

}
