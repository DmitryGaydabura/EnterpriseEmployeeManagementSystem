package com.example.demowithtests.util.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.example.demowithtests.util.annotations.LogColorConstants.*;

@Aspect
@Component
public class LogExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

    @Around("@annotation(com.example.demowithtests.util.annotations.LoggingExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.debug(ANSI_CYAN + joinPoint.getSignature() + " executed in " + executionTime + "ms" + ANSI_RESET);
        return proceed;
    }
}
