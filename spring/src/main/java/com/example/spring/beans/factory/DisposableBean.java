package com.example.spring.beans.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author ryan
 * @date 2023/5/27 16:46
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
