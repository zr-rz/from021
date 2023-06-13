package com.example.spring.context;

import java.util.EventObject;

/**
 * @author ryan
 * @date 2023/6/12 0:56
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
