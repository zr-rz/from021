package com.example.basic.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author ryan
 * @date 2023/8/19 23:55
 */
@Service
@RocketMQMessageListener(topic = "string-topic", consumerGroup = "string_consumer")
public class StringConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("------- StringConsumer received:" + s);
    }
}
