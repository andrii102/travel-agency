package com.epam.finaltask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.epam.finaltask.service..*(..)) || " +
            "execution(* com.epam.finaltask.controller..*(..))"
            , throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        if(e instanceof RuntimeException){
            log.error("Runtime exception in {}: {}() with cause {}",
                    joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(),
                    e.getMessage() != null ? e.getMessage() : "NULL",
                    e);
        }else {
            log.warn("Checked exception in {}.{}() with cause = {}",
                    joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(),
                    e.getMessage() != null ? e.getMessage() : "NULL",
                    e);
        }
    }

    @Before("execution(* com.epam.finaltask.service.*.*(..))")
    public void logBeforeBusinessLogic(JoinPoint joinPoint) {
        log.info("Executing: {}", joinPoint.getSignature().toShortString());
    }

    @Around("execution(* com.epam.finaltask.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("{} executed for {} ms", joinPoint.getSignature().getDeclaringType(), time);
        return returnValue;
    }

    @Before("execution(* com.epam.finaltask.controller..*(..)) || execution(* com.epam.finaltask.auth..*(..))")
    public void logSecurityEvent(JoinPoint joinPoint) {
        log.warn("Security Event: {}", joinPoint.getSignature().toShortString());
    }

}
