package com.example.spring.ioc;

import com.example.spring.bean.Car;
import com.example.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/6/3 21:49
 */
public class PrototypeBeanTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = applicationContext.getBean("car", Car.class);
        Car car2 = applicationContext.getBean("car", Car.class);
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car1 == car2);
    }
}
