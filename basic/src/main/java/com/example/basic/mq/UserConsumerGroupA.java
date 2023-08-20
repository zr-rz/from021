package com.example.basic.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author ryan
 * @date 2023/8/19 23:58
 */
@Service
@RocketMQMessageListener(topic = "user-topic", consumerGroup = "user_consumerA")
public class UserConsumerGroupA implements RocketMQListener<User> {
    @Override
    public void onMessage(User user) {
        System.out.println("######## user_consumerA received: " + user + " ; age: " + user.getUserAge() + " ; name: " + user.getUserName());
    }
}
