package com.example.spring.beans.factory;

import com.example.spring.beans.BeansException;

/**
 * 实现该接口能感知所属BeanFactory
 *
 * @author ryan
 * @date 2023/5/28 16:56
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
    
}
