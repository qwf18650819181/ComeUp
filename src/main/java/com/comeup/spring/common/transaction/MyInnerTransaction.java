package com.comeup.spring.common.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月11日 0011
 * @version: 1.0
 */
@Component
public class MyInnerTransaction {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public void test(){
        System.out.println("1123 Inner start");

        jdbcTemplate.execute(" update sa_store set update_date = now() ");

//        int i = 1 / 0;
        System.out.println("1123 Inner end ");

    }

}
