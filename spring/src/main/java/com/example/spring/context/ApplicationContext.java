package com.example.spring.context;

import com.example.spring.beans.factory.HierarchicalBeanFactory;
import com.example.spring.beans.factory.ListableBeanFactory;
import com.example.spring.core.io.ResourceLoader;

/**
 * @author ryan
 * @date 2023/3/13 11:47
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
