package com.example.spring.beans.factory.support;

import com.example.spring.beans.BeansException;
import com.example.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 简单的bean实例化策略，适用于无参构造函数实例化对象
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
