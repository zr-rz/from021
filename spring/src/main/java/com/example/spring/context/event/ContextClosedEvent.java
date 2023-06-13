package com.example.spring.context.event;

/**
 * @author ryan
 * @date 2023/6/12 1:13
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
