package com.example.algorithm.others;

public class Singleton {

    public int num = 1;
    private volatile static Singleton singleton;
    private Singleton () {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
