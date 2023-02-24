package com.example.algorithm.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintAbcUtil {
    private int num;
    private Object lock = new Object();

    public void print(int target, String name) {
        for (int i = 0; i < 2; i++) {
            synchronized (lock) {
                while (num % 3 != target) {
                    log.info(name + " is waiting");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info(name + " get run time");
                }
                System.out.println(name);
                num++;
                lock.notifyAll();
            }
        }
    }
}
