package com.example.spring.beans.factory.config;

import com.example.spring.beans.factory.HierarchicalBeanFactory;

/**
 * @author ryan
 * @date 2023/2/26 16:05
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    /**
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean
     */
    void destroySingletons();
}
