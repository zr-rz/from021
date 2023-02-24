package com.example.algorithm.thread;

public class JoinABC {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new printJoinABC(null), "a");
            Thread t2 = new Thread(new printJoinABC(t1), "b");
            Thread t3 = new Thread(new printJoinABC(t2), "c");
            t1.start();
            t2.start();
            t3.start();
            Thread.sleep(10);
        }
    }

    static class printJoinABC implements Runnable {
        private Thread before;

        public printJoinABC(Thread before) {
            this.before = before;
        }

        @Override
        public void run() {
            if (before != null) {
                try {
                    before.join();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
