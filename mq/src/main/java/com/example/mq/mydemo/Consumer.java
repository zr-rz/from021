package com.example.mq.mydemo;

public class Consumer {
    private Broker broker;

    public void connectBroker(Broker broker) {
        this.broker = broker;
    }

    public String syncPullMsg() {
        if (broker == null) {
            throw new RuntimeException("please connect broker first");
        }
        return broker.getMsg();
    }
}
