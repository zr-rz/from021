package com.example.spring.beans.factory.config;

import com.example.spring.beans.BeansException;

/**
 * @author ryan
 * @date 2023/6/18 22:33
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在bean实例化之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
