package com.wyj.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aop横切
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.wyj.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(){
        logger.info("----------Before----------");
    }

    @After("log()")
    public void doAfter(){
        logger.info("----------After----------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("ResultMethod : {}", result);
    }

}
