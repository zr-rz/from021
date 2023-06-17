package com.example.spring.aop;

/**
 * 被代理的对象
 *
 * @author ryan
 * @date 2023/6/15 22:43
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
