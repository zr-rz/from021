package com.example.pattern.observe;

import com.example.model.Subject;

public class HexaObserver extends Observer{

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("hex observer: " + Integer.toHexString(subject.getState()));
    }
}
