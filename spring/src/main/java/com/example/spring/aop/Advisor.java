package com.example.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * @author ryan
 * @date 2023/6/18 21:41
 */
public interface Advisor {

    Advice getAdvice();
}
