package com.comeup.spring.beanfactoryprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @auth: qwf
 * @date: 2024年1月24日 0024
 * @description:
 *  处理顺序:
 *      PriorityOrdered
 *      Ordered
 *      no order
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor,PriorityOrdered, Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor postProcessBeanFactory");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
