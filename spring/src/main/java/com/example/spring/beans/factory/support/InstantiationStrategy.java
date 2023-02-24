package com.example.spring.beans.factory.support;

import com.example.spring.beans.factory.config.BeanDefinition;

/**
 * Bean的实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition);
}
