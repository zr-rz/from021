package com.example.spring;

import cn.hutool.core.io.IoUtil;
import com.example.spring.beans.PropertyValue;
import com.example.spring.beans.PropertyValues;
import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.config.BeanReference;
import com.example.spring.beans.factory.support.DefaultListableBeanFactory;
import com.example.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.spring.core.io.DefaultResourceLoader;
import com.example.spring.core.io.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
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

    @Test
    void testPopulateBeanWithBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册car
        PropertyValues propertyValuesForCar = new PropertyValues();
        PropertyValue propertyValueCar = new PropertyValue("brand", "benz");
        propertyValuesForCar.addPropertyValue(propertyValueCar);
        BeanDefinition beanDefinitionForCar = new BeanDefinition(Car.class, propertyValuesForCar);
        beanFactory.registerBeanDefinition("car", beanDefinitionForCar);

        // 注册person
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue name = new PropertyValue("name", "peter");
        PropertyValue age = new PropertyValue("age", 18);
        PropertyValue car = new PropertyValue("car", new BeanReference("car"));
        propertyValues.addPropertyValue(name);
        propertyValues.addPropertyValue(age);
        propertyValues.addPropertyValue(car);
        beanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    @Test
    void testResourceLoader() throws IOException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        // classpath
        Resource resource = defaultResourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

        resource = defaultResourceLoader.getResource("src/test/resources/hello.txt");
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

        resource = defaultResourceLoader.getResource("https://www.baidu.com");
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    void testXmlFile() throws IOException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
