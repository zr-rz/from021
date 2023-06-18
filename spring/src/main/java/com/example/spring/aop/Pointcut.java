package com.example.spring.aop;

/**
 * 切点抽象
 *
 * @author ryan
 * @date 2023/6/13 22:51
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
