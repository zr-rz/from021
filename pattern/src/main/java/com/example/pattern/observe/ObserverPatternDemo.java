package com.example.pattern.observe;

import com.example.model.Subject;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        HexaObserver hexaObserver = new HexaObserver(subject);
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    subject.setState(finalI);
                }
            });
            thread.setName("t" + finalI);
            thread.start();
        }
    }
}
