package com.example.spring.beans.factory.support;

import com.example.spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册表接口
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 根据名称查找BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 是否包含beanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回定义的所有bean的名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
