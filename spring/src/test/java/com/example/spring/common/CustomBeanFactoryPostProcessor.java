package com.example.spring.common;

import com.example.spring.beans.PropertyValue;
import com.example.spring.beans.PropertyValues;
import com.example.spring.beans.factory.ConfigurableListableBeanFactory;
import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.example.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author ryan
 * @date 2023/2/28 13:37
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition personDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personDefinition.getPropertyValues();
        // 将person name改为zhang
        propertyValues.addPropertyValue(new PropertyValue("name", "zhang"));
    }
}
