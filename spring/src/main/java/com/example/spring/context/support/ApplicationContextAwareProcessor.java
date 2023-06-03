package com.example.spring.context.support;

import com.example.spring.beans.factory.config.BeanPostProcessor;
import com.example.spring.context.ApplicationContext;
import com.example.spring.context.ApplicationContextAware;

/**
 * @author ryan
 * @date 2023/6/2 8:21
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
