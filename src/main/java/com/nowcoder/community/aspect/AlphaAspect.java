package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Aop的切面编程
 *  around before
 *  before...
 *  afterReturning..
 *  after...
 *  around after
 * @author 尚郑
 */
//@Component
//@Aspect
public class AlphaAspect {

    // 切入点
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(){
        System.out.println("before...");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("after...");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning..");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("afterThrowing..");
    }

    // 切点前、后
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around before");
        Object proceed = joinPoint.proceed();
        System.out.println("around after");
        return proceed;
    }
}
