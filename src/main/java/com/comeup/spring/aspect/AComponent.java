package com.comeup.spring.aspect;

import com.comeup.spring.component.B;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Named
public class AComponent {

    public void test() {
        System.out.println("a");
    }

}
