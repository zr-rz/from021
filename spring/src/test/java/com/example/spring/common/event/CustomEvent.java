package com.example.spring.common.event;

import com.example.spring.context.event.ApplicationContextEvent;

/**
 * @author ryan
 * @date 2023/6/12 23:04
 */
public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(Object source) {
        super(source);
    }
}
