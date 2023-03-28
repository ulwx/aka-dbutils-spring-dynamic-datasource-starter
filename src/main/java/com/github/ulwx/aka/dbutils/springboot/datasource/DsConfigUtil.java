
package com.github.ulwx.aka.dbutils.springboot.datasource;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public final class DsConfigUtil {


    public static Map<String, Method> getSetterMethods(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                Method writeMethod = pd.getWriteMethod();
                if (writeMethod != null) {
                    if(pd.getName().equals("class")) continue;
                    methodMap.put(pd.getName(), writeMethod);
                }
            }
        } catch (Exception ignore) {
        }
        return methodMap;
    }

    public static Map<String, Method> getGetterMethods(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                Method readMethod = pd.getReadMethod();
                if (readMethod != null) {
                    if(pd.getName().equals("class")) continue;
                    methodMap.put(pd.getName(), readMethod);
                }
            }
        } catch (Exception ignore) {
        }
        return methodMap;
    }

}