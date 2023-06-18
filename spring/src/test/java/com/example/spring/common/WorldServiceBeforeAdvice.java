package com.example.spring.common;

import com.example.spring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author ryan
 * @date 2023/6/18 21:16
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: do something before the earth explode");
    }
}
