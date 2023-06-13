package com.example.spring.context.event;

import com.example.spring.context.ApplicationContext;
import com.example.spring.context.ApplicationEvent;

/**
 * @author ryan
 * @date 2023/6/12 1:11
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
