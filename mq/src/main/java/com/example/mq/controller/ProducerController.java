package com.example.mq.controller;

import com.example.mq.mydemo.User;
import com.example.mq.service.ProducerService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryan
 * @date 2023/8/19 22:52
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    ProducerService producerService;
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @PostMapping("/string")
    public SendResult sendString(@RequestBody String message) {
        return producerService.sendString(message);
    }

    @PostMapping("/user")
    public SendResult sendUser(@RequestBody User user) {
        return producerService.sendUser(user);
    }

    @PostMapping("/asyncSend")
    public void asyncSend(@RequestBody User message) {
        producerService.asyncSend(message);
    }

}
