package com.comeup.spring.common.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @author: qwf
 * @date: 2024年2月27日 0027
 * @version: 1.0
 * @description: TODO
 */
@Named
//@DependsOn("b")
public class A implements InitializingBean {

    @Autowired
    private B b;
    @Autowired
    Environment environment;
    @Autowired
    ApplicationContext applicationContext;


    public String getProperty(String name) {
        return environment.getProperty(name);
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(A.class);

    @PostConstruct
    public void test() {
        LOGGER.info("a start 1");
        System.out.println("a");
    }

    /**
     * @param :
     * @return void
     * @author qiu wanzi
     * @description afterPropertiesSet
     * @date 2024年2月27日 0027
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("a start 2");
        System.out.println("a");
    }

    /**
     * @param :
     * @return void
     * @author qiu wanzi
     * @description 产生循环依赖问题
     * @date 2024年2月27日 0027
     */
//    @Async
//    public void doSome() {
//        LOGGER.info("a doSome ....");
//    }


}
