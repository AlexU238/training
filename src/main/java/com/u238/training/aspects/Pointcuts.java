package com.u238.training.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Pointcuts {
    @Pointcut("execution(* com.u238.training.controllers.*.*(..))")
    void forControllerPackage() {

    }

    @Pointcut("execution(* com.u238.training.dao.*.*(..))")
    void forDAOPackage() {

    }

    @Pointcut("execution(* com.u238.training.rest.*.*(..))")
    void forRestPackage() {

    }

}
