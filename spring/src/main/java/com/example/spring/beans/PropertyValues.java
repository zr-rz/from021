package com.example.spring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue pv = propertyValueList.get(i);
            if (pv.getName().equals(propertyValue.getName())) {
                // 重复属性值 覆盖
                propertyValueList.set(i, propertyValue);
                return;
            }
        }
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue item : propertyValueList) {
            if (item.getName().equals(propertyName)) {
                return item;
            }
        }
        return null;
    }
}
