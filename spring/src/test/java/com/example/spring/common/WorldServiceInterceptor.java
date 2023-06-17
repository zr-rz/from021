package com.example.spring.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author ryan
 * @date 2023/6/15 23:17
 */
public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Do something before the earth explodes");
        Object result = invocation.proceed();
        System.out.println("Do something after the earth explodes");
        return result;
    }
}
