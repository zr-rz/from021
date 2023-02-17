package com.example.mq.mydemo;

public class MyMQTest {
    public static void main(String[] args) {
        Broker broker = new Broker();
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        producer1.connectBroker(broker);
        producer2.connectBroker(broker);

        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        consumer1.connectBroker(broker);
        consumer2.connectBroker(broker);

        for (int i = 0; i < 2; i++) {
            producer1.asyncSendMsg("produce1 send msg" + i);
            producer2.asyncSendMsg("produce2 send msg" + i);
        }
        System.out.println("broker has msg : " + broker.getAllMsgByDisk());

        for (int i = 0; i < 1; i++) {
            System.out.println("consumer1 consume msg: " + consumer1.syncPullMsg());
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("consumer2 consume msg: " + consumer2.syncPullMsg());
        }
    }
}
