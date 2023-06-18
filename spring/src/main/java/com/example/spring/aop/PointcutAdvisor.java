package com.example.spring.aop;

/**
 * @author ryan
 * @date 2023/6/18 21:42
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
