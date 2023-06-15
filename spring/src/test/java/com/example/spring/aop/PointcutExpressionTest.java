package com.example.spring.aop;

import com.example.spring.aop.aspectj.AspectJExpressionPointcut;
import com.example.spring.service.HelloService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author ryan
 * @date 2023/6/15 22:13
 */
public class PointcutExpressionTest {
    @Test
    public void testPointcutExpression() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.example.spring.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }
}
