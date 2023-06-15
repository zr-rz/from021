package com.example.spring.ioc;

import com.example.spring.bean.Car;
import com.example.spring.bean.Person;
import com.example.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/5/24 23:58
 */
public class ApplicationContextTest {
    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
    }
}
