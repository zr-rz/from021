package com.example.spring.common;

import com.example.spring.bean.Car;
import com.example.spring.beans.factory.FactoryBean;

/**
 * @author ryan
 * @date 2023/6/4 21:55
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
