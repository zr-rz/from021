package com.example.spring.context;

/**
 * 事件发布者接口
 *
 * @author ryan
 * @date 2023/6/12 0:57
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
