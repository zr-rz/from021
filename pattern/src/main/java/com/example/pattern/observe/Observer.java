package com.example.pattern.observe;

import com.example.model.Subject;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
