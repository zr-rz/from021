package com.example.spring.context.event;

import com.example.spring.ApplicationListener;
import com.example.spring.context.ApplicationEvent;

/**
 * @author ryan
 * @date 2023/6/12 1:04
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
