package com.example.spring.context.support;

import com.example.spring.beans.BeansException;
import com.example.spring.beans.factory.ConfigurableListableBeanFactory;
import com.example.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.example.spring.beans.factory.config.BeanPostProcessor;
import com.example.spring.context.ConfigurableApplicationContext;
import com.example.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文
 *
 * @author ryan
 * @date 2023/3/13 11:50
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() {
        // 创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 添加ApplicationContextAwareProcessor，让继承ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor需要提前于其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);

        // 提前实例化单例bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 创建beanFactory并加载beanDefinition
     */
    protected abstract void refreshBeanFactory();

    /**
     * bean实例化之前，执行BeanFactoryPostProcessor
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =
                beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册beanPostProcessor
     * @param beanFactory
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public void close() {
        doClose();
    }

    @Override
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(new Runnable() {
            @Override
            public void run() {
                doClose();
            }
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose() {
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
