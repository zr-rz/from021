package com.example.spring;

import com.example.spring.bean.Car;
import com.example.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/6/4 21:59
 */
public class FactoryBeanTest {
    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car.getBrand());
    }
}
