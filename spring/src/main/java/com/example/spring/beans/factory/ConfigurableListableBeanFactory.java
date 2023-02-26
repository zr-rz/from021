package com.example.spring.beans.factory;

import com.example.spring.beans.BeansException;
import com.example.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author ryan
 * @date 2023/2/26 15:59
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 根据名称查找BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
