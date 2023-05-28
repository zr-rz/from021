package com.example.spring;

import com.example.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ryan
 * @date 2023/5/27 23:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.close();
    }
}
