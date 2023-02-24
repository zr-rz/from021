package com.example.algorithm.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintOddEven {
    private int count;
    private Object lock = new Object();
    public PrintOddEven(int count) {
        this.count = count;
    }

    private void print() {
        synchronized (lock) {
            while (count < 10) {
                System.out.print(Thread.currentThread().getName() + ":");
                System.out.println(++count);
                try {
                    lock.notifyAll();
                    log.info(Thread.currentThread().getName() + " waiting");
                    lock.wait();
                    log.info(Thread.currentThread().getName() + " after waiting");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.notifyAll();
        }
//        lock.notifyAll();
    }

    public static void main(String[] args) {
        PrintOddEven printOddEven = new PrintOddEven(0);
        Thread odd = new Thread(() -> printOddEven.print(), "odd");
        odd.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(printOddEven::print, "even").start();
    }
}
