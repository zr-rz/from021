package com.example.spring.common.event;

import com.example.spring.ApplicationListener;
import com.example.spring.context.event.ContextRefreshedEvent;

/**
 * @author ryan
 * @date 2023/6/12 23:03
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
