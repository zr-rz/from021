package com.example.spring;

import com.example.spring.context.support.ClassPathXmlApplicationContext;
import com.example.spring.service.HelloService;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/6/3 20:50
 */
public class AwareInterfaceTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        System.out.println(helloService.getBeanFactory());
        System.out.println(helloService.getApplicationContext());
    }
}
