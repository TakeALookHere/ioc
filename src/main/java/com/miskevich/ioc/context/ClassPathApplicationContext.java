package com.miskevich.ioc.context;

import com.miskevich.ioc.exception.BeanNotFoundException;
import com.miskevich.ioc.model.Bean;
import com.miskevich.ioc.model.BeanDefinition;
import com.miskevich.ioc.reader.BeanReader;
import com.miskevich.ioc.reader.XMLBeanReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassPathApplicationContext implements ApplicationContext {

    private BeanReader reader;
    private List<Bean> beans;
    private List<BeanDefinition> beanDefinitions;

    public ClassPathApplicationContext(String path) {
        this(new String[]{path});
    }

    public ClassPathApplicationContext(String[] paths) {
        beans = new ArrayList<>();
        setBeanReader(new XMLBeanReader());
        beanDefinitions = reader.getBeanDefinitions(paths);
        initializeBeanObjects();
        Injector injector = new Injector();
        injector.setBeans(beans);
        injector.injectBeanProperties(beanDefinitions);
    }

    public Object getBean(String id) {
        for (Bean bean : beans) {
            if (bean.getId().equals(id)) {
                return bean.getValue();
            }
        }
        throw new BeanNotFoundException("No such bean was registered with id: " + id);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        for (Bean bean : beans) {
            if (clazz.isInstance(bean.getValue())) {
                return (T) bean.getValue();
            }
        }
        throw new BeanNotFoundException("No such bean was registered for class: " + clazz);
    }

    public <T> T getBean(String id, Class<T> clazz) {
        for (Bean bean : beans) {
            if (bean.getId().equals(id)) {
                try {
                    return clazz.cast(bean.getValue());
                } catch (ClassCastException e) {
                    throw new BeanNotFoundException("No such bean was registered for class: " + clazz + " with id: " + id);
                }
            }
        }
        throw new BeanNotFoundException("No such bean was registered for class: " + clazz + " with id: " + id);
    }


    public List<String> getBeanNames() {
        return beans.stream().map(Bean::getId).collect(Collectors.toList());
    }

    public void setBeanReader(BeanReader reader) {
        this.reader = reader;
    }

    private void initializeBeanObjects() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Class clazz = Class.forName(beanDefinition.getClassName());
                Object instance = clazz.newInstance();
                createBeanFromBeanDefinition(instance, beanDefinition);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createBeanFromBeanDefinition(Object instance, BeanDefinition beanDefinition) {
        Bean bean = new Bean();
        bean.setId(beanDefinition.getId());
        bean.setValue(instance);
        beans.add(bean);
    }
}
