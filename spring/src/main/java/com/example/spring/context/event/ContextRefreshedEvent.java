package com.example.spring.context.event;

/**
 * @author ryan
 * @date 2023/6/12 1:14
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
