package com.comeup.spring.factorybean;

import com.comeup.spring.component.A;
import org.springframework.beans.factory.FactoryBean;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 */
public class MyFactoryBean implements FactoryBean<A> {
    @Override
    public A getObject() throws Exception {
        return new A();
    }

    @Override
    public Class<?> getObjectType() {
        return A.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
