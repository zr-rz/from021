package com.example.spring.aop;

import java.lang.reflect.Method;

/**
 * @author ryan
 * @date 2023/6/13 23:01
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}
