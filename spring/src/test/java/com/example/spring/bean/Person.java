package com.example.spring.bean;

import com.example.spring.bean.Car;
import com.example.spring.beans.factory.DisposableBean;
import com.example.spring.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {
    private String name;
    private Integer age;

    private Car car;

    public void customInitMethod() {
        System.out.println("I was born in the method named customInitMethod");
    }

    public void customDestroyMethod() {
        System.out.println("I died in the method named customDestroyMethod");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("I was born in the method named afterPropertiesSet");
    }

    @Override
    public void destroy() {
        System.out.println("I died in the method named destroy");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
