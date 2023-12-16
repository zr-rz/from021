package com.example.model;

import com.example.pattern.observe.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public synchronized void setState(int state) {
        this.state = state;
        notifyALlObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyALlObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
