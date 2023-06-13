package com.example.spring;

import com.example.spring.context.ApplicationEvent;

import java.util.EventListener;

/**
 * @author ryan
 * @date 2023/6/12 0:59
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
