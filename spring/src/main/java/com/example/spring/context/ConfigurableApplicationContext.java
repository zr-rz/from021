package com.example.spring.context;

/**
 * @author ryan
 * @date 2023/3/13 11:48
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     */
    void refresh();
}
