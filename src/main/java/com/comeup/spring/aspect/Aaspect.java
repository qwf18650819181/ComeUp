package com.comeup.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description:
 */
@Component
@Aspect
public class Aaspect {

    @Before("execution(* AComponent.test(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before executing " + joinPoint.getSignature().getName());
    }

    @After("execution(* AComponent.test(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("Before executing " + joinPoint.getSignature().getName());
    }

    @Around("execution(* AComponent.test(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before aroundAdvice " + joinPoint.getSignature().getName());
        Object object = joinPoint.proceed();
        System.out.println("after aroundAdvice " + joinPoint.getSignature().getName());
        return object;
    }
}
