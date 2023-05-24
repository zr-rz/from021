package com.example.spring.context.support;

/**
 * @author ryan
 * @date 2023/3/13 22:42
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    /**
     * 从xml文件加载BeanDefinition，并自动刷新上下文
     * @param configLocation
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
