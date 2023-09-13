package com.spring.localhongdae.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "execution(* com.spring.localhongdae..*.*(..))")
    private void logPointCut() {
    }

    @Around(value = "logPointCut()")
    private Object logAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("#### Method Start : " + joinPoint.getSignature().getName() + " ####");
        Object result = joinPoint.proceed();
        log.info("#### Method End : " + joinPoint.getSignature().getName() + " ####");
        return result;
    }
}
