package com.miskevich.ioc.util;

import com.miskevich.ioc.data.Bean;
import com.miskevich.ioc.data.BeanDefinition;
import com.miskevich.ioc.util.reader.BeanReader;

import java.util.List;

public class ClassPathApplicationContext implements ApplicationContext {

    private BeanReader reader;
    private List<Bean> beans;
    private List<BeanDefinition> beanDefinitions;

    public Object getBean(String id) {
        return null;
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
}
