package com.example.spring.beans.factory.support;

import com.example.spring.beans.factory.BeanFactory;
import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.config.ConfigurableBeanFactory;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if (bean == null) {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            return createBean(name, beanDefinition);
        }
        return bean;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String beanName);
}
