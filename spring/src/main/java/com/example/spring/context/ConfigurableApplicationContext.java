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

    /**
     * 关闭应用上下文
     */
    void close();

    /**
     * 向虚拟机中注册一个钩子方法，在虚拟机关闭之前执行关闭容器等操作
     */
    void registerShutdownHook();
}
