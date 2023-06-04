package com.example.spring.beans.factory;

/**
 * @author ryan
 * @date 2023/6/4 21:32
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();
}
