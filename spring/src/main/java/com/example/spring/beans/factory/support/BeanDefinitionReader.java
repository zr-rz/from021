package com.example.spring.beans.factory.support;

import com.example.spring.beans.BeansException;
import com.example.spring.core.io.Resource;
import com.example.spring.core.io.ResourceLoader;

import java.io.IOException;

/**
 * 读取bean定义信息的接口
 *
 * @author ryan
 * @date 2023/2/26 15:02
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String[] locations);
}
