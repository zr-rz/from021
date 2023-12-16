package com.example.pattern.observe;

import com.example.model.Subject;

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println("binary observer: " + Integer.toBinaryString(subject.getState()));
    }
}
