package com.comeup.spring.common.initializiingbean;

import com.comeup.spring.common.aspect.AComponent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Objects;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 *
 * PostConstruct 是j2ee规范
 * InitializingBean 是spring扩展
 */
@Named
public class MyInitializingBean extends ApplicationObjectSupport implements InitializingBean  {
    @Override
    public void afterPropertiesSet() throws Exception {
        AComponent bean = Objects.requireNonNull(this.getApplicationContext()).getBean(AComponent.class);
        System.out.println("MyInitializingBean afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("MyInitializingBean PostConstruct");
    }
}
