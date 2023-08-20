package com.example.spring.aop;

import com.example.spring.context.support.ClassPathXmlApplicationContext;
import com.example.spring.service.WorldService;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/6/18 23:29
 */
public class AutoProxyTest {

    @Test
    public void testAutoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }
}
