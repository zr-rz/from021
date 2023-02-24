package com.example.algorithm.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockABC {
    private int num;
    private Lock lock = new ReentrantLock();

    public void print(int target) {
        for (int i = 0; i < 3; ) {
            lock.lock();
            if (num % 3 == target) {
                System.out.println(Thread.currentThread().getName());
                i++;
                num++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockABC lockABC = new LockABC();
        new Thread(() -> {lockABC.print(0);}, "a").start();
        new Thread(() -> {lockABC.print(1);}, "b").start();
        new Thread(() -> {lockABC.print(2);}, "c").start();
    }
}
