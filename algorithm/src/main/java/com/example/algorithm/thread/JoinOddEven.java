package com.example.algorithm.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class JoinOddEven {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10;) {
            Thread t1 = new Thread(new OddEven(null, i++), "even");
            Thread t2 = new Thread(new OddEven(t1, i++), "odd");
            t1.start();
            t2.start();
            Thread.sleep(10);
        }
    }


    static class OddEven implements Runnable{
        private Thread beforeHand;
        private int num;

        public OddEven (Thread beforeHand, int num) {
            this.beforeHand = beforeHand;
            this.num = num;
        }

        @Override
        public void run() {
            if (beforeHand != null) {
                try {
                    beforeHand.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + num);

        }
    }
}
