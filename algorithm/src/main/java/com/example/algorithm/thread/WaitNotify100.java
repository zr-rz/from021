package com.example.algorithm.thread;

public class WaitNotify100 {

    private int num;
    private int maxNum = 10;

    private Object lock = new Object();

    public void print(int target) {
        while (true) {
            synchronized (lock) {
                while (num % 3 != target) {
                    if (num >= maxNum) {
                        break;
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (num >= maxNum) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + num);
                num++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotify100 waitNotify100 = new WaitNotify100();
        new Thread(() -> waitNotify100.print(0), "a").start();
        new Thread(() -> waitNotify100.print(1), "b").start();
        new Thread(() -> waitNotify100.print(2), "c").start();
    }
}
