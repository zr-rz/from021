package com.example.spring.common.event;

import com.example.spring.ApplicationListener;
import com.example.spring.context.event.ContextClosedEvent;

/**
 * @author ryan
 * @date 2023/6/12 23:02
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
