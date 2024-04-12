package com.comeup.spring.common.transaction;

import com.comeup.spring.common.aop.OperaLog;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyTransaction {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyInnerTransaction myInnerTransaction;

    @OperaLog("测试")
    @Transactional(propagation = Propagation.REQUIRED)
    public void test(){
        System.out.println("1123 start");

        jdbcTemplate.execute(" update sa_store set update_date = now() ");

        try {
            myInnerTransaction.test();
        } catch (Exception e) {
            log.error("inner error", e);
        }

        System.out.println("1123 end ");


    }

}
