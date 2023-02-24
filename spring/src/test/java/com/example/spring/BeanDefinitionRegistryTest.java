package com.example.spring;

import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

public class BeanDefinitionRegistryTest extends ApplicationTests{

    @Test
    void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello();
    }
}
