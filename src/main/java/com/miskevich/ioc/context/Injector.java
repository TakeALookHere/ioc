package com.miskevich.ioc.context;

import com.miskevich.ioc.exception.BeanInstantiationException;
import com.miskevich.ioc.exception.BeanNotFoundException;
import com.miskevich.ioc.model.Bean;
import com.miskevich.ioc.model.BeanDefinition;
import com.miskevich.ioc.model.BeanProperty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Injector {

    private List<Bean> beans;

    void injectBeanProperties(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Class clazz = Class.forName(beanDefinition.getClassName());
                Method[] declaredMethods = clazz.getDeclaredMethods();
                Bean bean = getBeanById(beanDefinition.getId());

                List<BeanProperty> beanProperties = beanDefinition.getBeanProperties();
                for (BeanProperty beanProperty : beanProperties) {
                    String setterName = generateSetterName(beanProperty);
                    boolean isSetterFound = false;

                    for (Method method : declaredMethods) {
                        if (method.getName().equals(setterName)) {
                            isSetterFound = true;
                            injectPropertyIntoSetter(bean, beanProperty, method);
                            break;
                        }
                    }
                    if (!isSetterFound) {
                        throw new BeanInstantiationException("No setter was found in class " + beanDefinition.getClassName()
                                + " for field " + beanProperty.getName());
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new BeanInstantiationException(e);
            }
        }
    }

    void injectPropertyIntoSetter(Bean bean, BeanProperty beanProperty, Method method) {
        try {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object beanValue = bean.getValue();
            String beanPropertyValue = beanProperty.getValue();
            if (null != beanPropertyValue) {
                Class<?> parameterType = parameterTypes[0];
                if (parameterType.equals(int.class) || parameterType.equals(Integer.class)) {
                    method.invoke(beanValue, Integer.parseInt(beanPropertyValue));
                } else if (parameterType.equals(double.class) || parameterType.equals(Double.class)) {
                    method.invoke(beanValue, Double.parseDouble(beanPropertyValue));
                } else if (parameterType.equals(long.class) || parameterType.equals(Long.class)) {
                    method.invoke(beanValue, Long.parseLong(beanPropertyValue));
                } else if (parameterType.equals(float.class) || parameterType.equals(Float.class)) {
                    method.invoke(beanValue, Float.parseFloat(beanPropertyValue));
                } else if (parameterType.equals(short.class) || parameterType.equals(Short.class)) {
                    method.invoke(beanValue, Short.parseShort(beanPropertyValue));
                } else if (parameterType.equals(boolean.class) || parameterType.equals(Boolean.class)) {
                    method.invoke(beanValue, Boolean.parseBoolean(beanPropertyValue));
                } else if (parameterType.equals(byte.class) || parameterType.equals(Byte.class)) {
                    method.invoke(beanValue, Byte.parseByte(beanPropertyValue));
                } else if (parameterType.equals(char.class) || parameterType.equals(Character.class)) {
                    method.invoke(beanValue, beanPropertyValue.charAt(0));
                } else {
                    method.invoke(beanValue, beanPropertyValue);
                }
            } else {
                Bean beanByRef = getBeanById(beanProperty.getRef());
                method.invoke(beanValue, beanByRef.getValue());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new BeanInstantiationException(e);
        }
    }

    private String generateSetterName(BeanProperty beanProperty) {
        String beanPropertyName = beanProperty.getName();
        return "set" +
                beanPropertyName.substring(0, 1).toUpperCase() +
                beanPropertyName.substring(1);
    }

    private Bean getBeanById(String id) {
        for (Bean bean : beans) {
            if (bean.getId().equals(id)) {
                return bean;
            }
        }
        throw new BeanNotFoundException("No such bean was registered: " + id);
    }

    void setBeans(List<Bean> beans) {
        this.beans = beans;
    }
}
