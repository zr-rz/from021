package com.example.spring.context.event;

import com.example.spring.ApplicationListener;
import com.example.spring.beans.BeansException;
import com.example.spring.beans.factory.BeanFactory;
import com.example.spring.beans.factory.BeanFactoryAware;
import com.example.spring.context.ApplicationEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan
 * @date 2023/6/12 1:07
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
