package com.example.spring.beans.factory.config;

import com.example.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改BeanDefinition的属性值
 *
 * @author ryan
 * @date 2023/2/28 13:11
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有beanDefinition加载完成之后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
