package com.example.spring.beans.factory;

import com.example.spring.beans.BeansException;

import java.util.Map;

/**
 * @author ryan
 * @date 2023/2/26 16:00
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 返回指定类型的所有实例
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回所有beanDefinition名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
