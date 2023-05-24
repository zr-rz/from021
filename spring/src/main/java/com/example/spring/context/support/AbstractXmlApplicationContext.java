package com.example.spring.context.support;

import com.example.spring.beans.factory.support.DefaultListableBeanFactory;
import com.example.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author ryan
 * @date 2023/3/13 22:23
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
