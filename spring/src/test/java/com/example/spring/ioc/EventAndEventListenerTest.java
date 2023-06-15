package com.example.spring.ioc;

import com.example.spring.bean.Car;
import com.example.spring.bean.Person;
import com.example.spring.common.event.CustomEvent;
import com.example.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author ryan
 * @date 2023/5/24 23:58
 */
public class EventAndEventListenerTest {
    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");

        applicationContext.publishEvent(new CustomEvent(applicationContext));
        applicationContext.registerShutdownHook();
    }
}
