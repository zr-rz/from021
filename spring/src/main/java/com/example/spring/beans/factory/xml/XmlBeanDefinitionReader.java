package com.example.spring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.example.spring.beans.BeansException;
import com.example.spring.beans.PropertyValue;
import com.example.spring.beans.factory.config.BeanDefinition;
import com.example.spring.beans.factory.config.BeanReference;
import com.example.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.example.spring.beans.factory.support.BeanDefinitionRegistry;
import com.example.spring.core.io.Resource;
import com.example.spring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ryan
 * @date 2023/2/26 17:08
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                if (BEAN_ELEMENT.equals(((Element) childNodes.item(i)).getNodeName())) {
                    // 解析bean标签
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                    String destroyMethodName = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);

                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeansException("Cannot find class [" + className + "]");
                    }
                    // id优先级高于name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    if (StrUtil.isEmpty(beanName)) {
                        // id和name都没有，取类名的第一个字母转为小写后作为bean的名称
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }

                    BeanDefinition beanDefinition = new BeanDefinition(clazz);
                    beanDefinition.setInitMethodName(initMethodName);
                    beanDefinition.setDestroyMethodName(destroyMethodName);

                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if (bean.getChildNodes().item(j) instanceof Element) {
                            if (PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())) {
                                // 解析property标签
                                Element property = (Element) bean.getChildNodes().item(j);
                                String propertyName = property.getAttribute(NAME_ATTRIBUTE);
                                String propertyValue = property.getAttribute(VALUE_ATTRIBUTE);
                                String propertyRef = property.getAttribute(REF_ATTRIBUTE);

                                if (StrUtil.isEmpty(propertyName)) {
                                    throw new BeansException("The name attribute cannot be null or empty");
                                }

                                Object value = propertyValue;
                                if (StrUtil.isNotEmpty(propertyRef)) {
                                    value = new BeanReference(propertyRef);
                                }
                                PropertyValue pv = new PropertyValue(propertyName, value);
                                beanDefinition.getPropertyValues().addPropertyValue(pv);
                            }
                        }
                    }

                    if (getRegistry().containsBeanDefinition(beanName)) {
                        // beanName不能重名
                        throw new BeansException("Duplicate beanName [" + beanName + "] is not allowed");
                    }
                    // 注册BeanDefinition
                    getRegistry().registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }
}
