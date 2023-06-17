package com.example.spring.aop;

import com.example.spring.aop.aspectj.AspectJExpressionPointcut;
import com.example.spring.aop.framework.JdkDynamicAopProxy;
import com.example.spring.common.WorldServiceInterceptor;
import com.example.spring.service.WorldService;
import com.example.spring.service.WorldServiceImpl;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/6/15 23:21
 */
public class DynamicProxyTest {

    @Test
    public void testJdkDynamicProxy() {
        WorldService worldService = new WorldServiceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.example.spring.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);

        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
}
