package com.comeup.spring.common.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年4月12日 0012
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class OperaLogAspect {

    @Pointcut("@annotation(com.comeup.spring.common.aop.OperaLog)")
    public void operateLog() {
    }

    @Around("operateLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperaLog annotation = method.getAnnotation(OperaLog.class);
        log.info("[请求开始{}] {} {} req: {}", annotation.value(), signature.getClass().getName(), method.getName(), JSON.toJSONString(signature.getParameterNames()));
        Object res = null;
        try {
            res = joinPoint.proceed();
            return res;
        } catch (Throwable t) {
            log.error("[请求失败{}] {} {} error: {}", annotation.value(), className(signature), method.getName(), t.getMessage());
            throw t;
        } finally {
            if (method.getReturnType().equals(Void.TYPE)) {
                log.info("[请求结束{}] {} {} res: void", annotation.value(), className(signature), method.getName());
            } else if (Objects.isNull(res)) {
                log.info("[请求结束{}] {} {} res: null", annotation.value(), className(signature), method.getName());
            } else {
                log.info("[请求结束{}] {} {} res: {}", annotation.value(), className(signature), method.getName(), JSON.toJSONString(res));
            }
        }
    }

    @NotNull
    private static String className(MethodSignature signature) {
        return signature.getDeclaringTypeName().substring(signature.getDeclaringTypeName().lastIndexOf('.'));
    }
}
