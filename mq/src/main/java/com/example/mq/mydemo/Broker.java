package com.example.mq.mydemo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

public class Broker {
    // 对应RocketMQ中MessageQueue，默认情况下1个Topic包含4个MessageQueue
    private LinkedBlockingDeque<String> messageQueue = new LinkedBlockingDeque<>(Integer.MAX_VALUE);

    public void sendMsg(String msg) {
        try {
            messageQueue.put(msg);
        } catch (InterruptedException e) {

        }
    }

    public String getMsg() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {

        }
        return null;
    }

    public String getAllMsgByDisk() {
        StringBuilder sb = new StringBuilder("\n");
        messageQueue.iterator().forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                sb.append(s + "\n");
            }
        });
        return sb.toString();
    }
}
