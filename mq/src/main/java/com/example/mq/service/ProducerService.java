package com.example.mq.service;

import com.example.mq.mydemo.User;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ryan
 * @date 2023/8/19 22:41
 */
@Service
public class ProducerService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public SendResult sendString(String message) {
        SendResult sendResult = rocketMQTemplate.syncSend("string-topic", message);
        System.out.println("syncSend String to string-topic sendResult=" + sendResult);
        return sendResult;
    }

    public SendResult sendUser(User user) {
        rocketMQTemplate.setMessageQueueSelector(new SelectMessageQueueByHash());
        SendResult sendResult = rocketMQTemplate.syncSendOrderly("user-topic", user, user.getUserName());
        System.out.println("syncSend User to user-topic sendResult=" + sendResult);
        return sendResult;
    }

    public void asyncSend(User message) {
        rocketMQTemplate.asyncSend("user-topic", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("send success: " + sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("send fail: " + throwable.getMessage());
            }
        });
    }

}
