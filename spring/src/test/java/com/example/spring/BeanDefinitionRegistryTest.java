package com.example.spring;

import com.example.spring.beans.PropertyValue;
import com.example.spring.beans.PropertyValues;
import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinitionRegistryTest extends ApplicationTests{

    @Test
    void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello();
    }

    @Test
    void testPopulateBeanWithPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue name = new PropertyValue("name", "peter");
        PropertyValue age = new PropertyValue("age", 18);
        propertyValues.addPropertyValue(name);
        propertyValues.addPropertyValue(age);
        beanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
}
