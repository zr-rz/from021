package com.example.spring.context;

import com.example.spring.beans.BeansException;
import com.example.spring.beans.factory.Aware;

/**
 * 实现该接口，能感知所属Application
 * @author ryan
 * @date 2023/6/2 8:17
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
