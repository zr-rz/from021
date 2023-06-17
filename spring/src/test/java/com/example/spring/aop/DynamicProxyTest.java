package com.example.spring.aop;

import com.example.spring.aop.aspectj.AspectJExpressionPointcut;
import com.example.spring.aop.framework.CglibAopProxy;
import com.example.spring.aop.framework.JdkDynamicAopProxy;
import com.example.spring.common.WorldServiceInterceptor;
import com.example.spring.service.WorldService;
import com.example.spring.service.WorldServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/6/15 23:21
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.example.spring.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
    }
    @Test
    public void testJdkDynamicProxy() {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
}
