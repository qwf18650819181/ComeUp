package com.comeup.spring.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Slf4j
public class ACondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String javaHome = context.getEnvironment().getProperty("java_home");
        log.info(javaHome);
        return true;
    }
}
