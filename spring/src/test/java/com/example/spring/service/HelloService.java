package com.example.spring.service;

import com.example.spring.beans.BeansException;
import com.example.spring.beans.factory.BeanFactory;
import com.example.spring.beans.factory.BeanFactoryAware;
import com.example.spring.context.ApplicationContext;
import com.example.spring.context.ApplicationContextAware;

/**
 * @author ryan
 * @date 2023/6/3 20:10
 */
public class HelloService implements ApplicationContextAware, BeanFactoryAware {
    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
