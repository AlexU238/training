package com.u238.training.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {

    private Logger logger = Logger.getLogger(this.getClass().getName());


    @After("com.u238.training.aspects.Pointcuts.forControllerPackage()")
    public void controllerLog(JoinPoint joinPoint) {
        logger.info("Controller Method: "+joinPoint.getSignature());
    }

    @After("com.u238.training.aspects.Pointcuts.forRestPackage()")
    public void restLog(JoinPoint joinPoint) {
        logger.info("Rest Controller Method: "+joinPoint.getSignature());
    }

    @AfterReturning(pointcut="com.u238.training.aspects.Pointcuts.forControllerPackage()",
            returning="result")
    public void webPageLog(Object result) {
        logger.info("Opening web page: "+result);
    }

    @Before("com.u238.training.aspects.Pointcuts.forDAOPackage()")
    public void daoLog(JoinPoint joinPoint){
        logger.info("DAO method: "+joinPoint.getSignature());
    }



}
