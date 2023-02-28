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

    /**
     * 根据名称和类型查找bean
     * @param name
     * @param requiredType
     * @return
     * @param <T>
     */
    <T> T getBean(String name, Class<T> requiredType);
}
