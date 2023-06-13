package com.example.spring.common.event;

import com.example.spring.ApplicationListener;

/**
 * @author ryan
 * @date 2023/6/12 23:05
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName());
    }
}
