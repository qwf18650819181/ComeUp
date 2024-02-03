package com.comeup.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.monitor.MonitorBus;
import com.yomahub.liteflow.property.LiteflowConfig;
import com.yomahub.liteflow.spi.spring.SpringAware;
import com.yomahub.liteflow.spring.ComponentScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 */
@Configuration
@ComponentScan
public class Main {


    @Bean
    public MonitorBus monitorBus(LiteflowConfig liteflowConfig) {
        return new MonitorBus(liteflowConfig);
    }
    @Bean
    @DependsOn("springAware")
    public FlowExecutor flowExecutor(LiteflowConfig liteflowConfig) {
        return new FlowExecutor(liteflowConfig);
    }
    @Bean
    public LiteflowConfig liteflowConfig() {
        LiteflowConfig liteflowConfig = new LiteflowConfig();
        liteflowConfig.setRuleSource("liteflow/flow.el.xml");
        liteflowConfig.setEnableLog(true);
        return liteflowConfig;
    }
    @Bean
    public ComponentScanner componentScanner() {
        return new ComponentScanner();
    }
    @Bean
    public SpringAware springAware() {
        return new SpringAware();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        FlowExecutor flowExecutor = (FlowExecutor) applicationContext.getBean("flowExecutor");

        flowExecutor.execute2Resp("chain1", "arg");

        System.out.println("chain1 end ");
    }


}
