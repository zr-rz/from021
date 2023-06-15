package com.example.spring.aop;

/**
 * @author ryan
 * @date 2023/6/13 22:52
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
