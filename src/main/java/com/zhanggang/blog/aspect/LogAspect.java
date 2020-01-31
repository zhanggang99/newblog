package com.zhanggang.blog.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.spi.CalendarDataProvider;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.zhanggang.blog.web.*.*(..))")
    public void log(){

    }
    @Before("log()")
    public void doBefore(){
        logger.info("-------before-------");
    }
    @After("log()")
    public void doAfter(){
        logger.info("------after-------");
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("result :{}",result);
    }
}
