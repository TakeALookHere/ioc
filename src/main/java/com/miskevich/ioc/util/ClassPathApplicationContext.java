package com.miskevich.ioc.util;

import com.miskevich.ioc.data.Bean;
import com.miskevich.ioc.data.BeanDefinition;
import com.miskevich.ioc.data.BeanProperty;
import com.miskevich.ioc.util.reader.BeanReader;
import com.miskevich.ioc.util.reader.XMLBeanReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassPathApplicationContext implements ApplicationContext {

    private BeanReader reader;
    private List<Bean> beans;
    private List<BeanDefinition> beanDefinitions;

    public ClassPathApplicationContext(String path) {
        beans = new ArrayList<>();
        setBeanReader(new XMLBeanReader());
        beanDefinitions = reader.getBeanDefinitions(path);
        initializeBeanObjects();
        injectBeanProperties();
    }

    public ClassPathApplicationContext(String[] paths) {
    }

    public Object getBean(String id) {
        for (Bean bean : beans) {
            if (bean.getId().equals(id)) {
                return bean.getValue();
            }
        }
        throw new BeanInstantiationException("No such bean was registered with id: " + id);
    }

    public <T> T getBean(Class<T> clazz) {
        return null;
    }

    public <T> T getBean(String id, Class<T> clazz) {
        return null;
    }

    public List<String> getBeanNames() {
        return null;
    }

    private void setBeanReader(BeanReader reader) {
        this.reader = reader;
    }

    private void initializeBeanObjects() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Class clazz = Class.forName(beanDefinition.getClassName());
                Object instance = clazz.newInstance();
                createBeansFromBeanDefinitions(instance, beanDefinition);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void injectBeanProperties() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Class clazz = Class.forName(beanDefinition.getClassName());
                Method[] declaredMethods = clazz.getDeclaredMethods();
                Bean beanById = getBeanById(beanDefinition);
                if (null == beanById.getId()) {
                    throw new BeanInstantiationException("No such bean was registered: " + beanDefinition.getId());
                }

                List<BeanProperty> beanProperties = beanDefinition.getBeanProperties();
                for (BeanProperty beanProperty : beanProperties) {
                    String setterName = generateSetterName(beanProperty);
                    boolean isSetterFound = false;
                    for (Method method : declaredMethods) {
                        if (method.getName().equals(setterName)) {
                            isSetterFound = true;
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (null != beanProperty.getValue()) {
                                if (parameterTypes[0].equals(int.class)) {
                                    method.invoke(beanById.getValue(), Integer.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(double.class)) {
                                    method.invoke(beanById.getValue(), Double.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(long.class)) {
                                    method.invoke(beanById.getValue(), Long.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(float.class)) {
                                    method.invoke(beanById.getValue(), Float.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(short.class)) {
                                    method.invoke(beanById.getValue(), Short.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(boolean.class)) {
                                    method.invoke(beanById.getValue(), Boolean.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(byte.class)) {
                                    method.invoke(beanById.getValue(), Byte.valueOf(beanProperty.getValue()));
                                } else if (parameterTypes[0].equals(char.class)) {
                                    method.invoke(beanById.getValue(), beanProperty.getValue().charAt(0));
                                } else {
                                    method.invoke(beanById.getValue(), beanProperty.getValue());
                                }
                                break;
                            } else {
                                Bean beanByRef = getBeanByRef(beanProperty);
                                if (null == beanByRef.getId()) {
                                    throw new BeanInstantiationException("No such bean was registered: " + beanProperty.getRef());
                                }
                                method.invoke(beanById.getValue(), beanByRef.getValue());
                            }
                        }
                    }
                    if (!isSetterFound) {
                        throw new BeanInstantiationException("No setter was found in class " + beanDefinition.getClassName()
                                + " for field " + beanProperty.getName());
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String generateSetterName(BeanProperty beanProperty) {
        String beanPropertyName = beanProperty.getName();
        return "set" +
                beanPropertyName.substring(0, 1).toUpperCase() +
                beanPropertyName.substring(1);
    }

    private Bean getBeanById(BeanDefinition beanDefinition) {
        Bean emptyBean = new Bean();
        for (Bean bean : beans) {
            if (bean.getId().equals(beanDefinition.getId())) {
                return bean;
            }
        }
        return emptyBean;
    }

    private Bean getBeanByRef(BeanProperty beanProperty) {
        Bean emptyBean = new Bean();
        for (Bean bean : beans) {
            if (bean.getId().equals(beanProperty.getRef())) {
                return bean;
            }
        }
        return emptyBean;
    }

    private void createBeansFromBeanDefinitions(Object instance, BeanDefinition beanDefinition) {
        Bean bean = new Bean();
        bean.setId(beanDefinition.getId());
        bean.setValue(instance);
        beans.add(bean);
    }
}
