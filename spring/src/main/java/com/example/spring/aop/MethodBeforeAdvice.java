package com.example.spring.aop;

import java.lang.reflect.Method;

/**
 * @author ryan
 * @date 2023/6/18 21:02
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;
}
