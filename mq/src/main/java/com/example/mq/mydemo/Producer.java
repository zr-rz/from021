package com.example.mq.mydemo;

public class Producer {
    private Broker broker;

    public void connectBroker(Broker broker) {
        this.broker = broker;
    }

    public void asyncSendMsg(String msg) {
        if (broker == null) {
            throw new RuntimeException("please connect broker first");
        }
        new Thread(()->{
            broker.sendMsg(msg);
        }).start();
    }
}
