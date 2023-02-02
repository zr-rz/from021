package com.example.spring.proxy;

import java.util.Arrays;

public class Man implements Person {
    @Override
    public void say(String name) {
        System.out.println("I'm " + name);
    }
}
