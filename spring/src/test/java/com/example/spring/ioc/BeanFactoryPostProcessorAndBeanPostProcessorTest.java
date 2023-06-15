package com.example.spring.ioc;

import com.example.spring.bean.Car;
import com.example.spring.bean.Person;
import com.example.spring.beans.factory.support.DefaultListableBeanFactory;
import com.example.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.spring.common.CustomBeanFactoryPostProcessor;
import com.example.spring.common.CustomerBeanPostProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest extends ApplicationTests{

    @Test
    void testBeanFactoryPostProcessor() throws IOException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }

    @Test
    void testBeanPostProcessor() throws IOException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
