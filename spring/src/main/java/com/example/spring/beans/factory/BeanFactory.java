package com.example.spring.beans.factory;

/**
 * bean容器
 */
public interface BeanFactory {
    /**
     * 获取bean
     * @param name
     * @return
     */
    Object getBean(String name);
}
