package com.miskevich.ioc.context;

import com.miskevich.ioc.config.BeanFactoryPostProcessor;
import com.miskevich.ioc.config.BeanPostProcessor;
import com.miskevich.ioc.exception.BeanInstantiationException;
import com.miskevich.ioc.exception.BeanNotFoundException;
import com.miskevich.ioc.model.Bean;
import com.miskevich.ioc.model.BeanDefinition;
import com.miskevich.ioc.reader.BeanReader;
import com.miskevich.ioc.reader.XMLBeanReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassPathApplicationContext implements ApplicationContext {

    private BeanReader reader;
    private List<Bean> beans;
    private List<BeanDefinition> beanDefinitions;
    private Map<String, Integer> classNameToCount;
    private List<BeanFactoryPostProcessor> beanFactoryPostProcessors;
    private List<BeanPostProcessor> beanPostProcessors;

    public ClassPathApplicationContext(String path) {
        this(new String[]{path});
    }

    public ClassPathApplicationContext(String[] paths) {
        beans = new ArrayList<>();
        beanFactoryPostProcessors = new ArrayList<>();
        beanPostProcessors = new ArrayList<>();

        setBeanReader(new XMLBeanReader());
        beanDefinitions = reader.getBeanDefinitions(paths);
        classNameToCount = reader.getClassNameToCount();

        boolean isBeanFactoryPostProcessorPresent = isSystemBeanPresent(BeanFactoryPostProcessor.class);
        boolean isBeanPostProcessorPresent = isSystemBeanPresent(BeanPostProcessor.class);

        if (isBeanFactoryPostProcessorPresent) {
            postProcessBeanFactory();
        }

        initializeBeanObjects();

        Injector injector = new Injector(beanDefinitions, beans);
        injector.injectBeanProperties();

        if (isBeanPostProcessorPresent) {
            postProcessBefore();
        }

        initMethod();

        if (isBeanPostProcessorPresent) {
            postProcessAfter();
        }
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
        if (classNameToCount.containsKey(clazz.getName()) && classNameToCount.get(clazz.getName()) > 1) {
            throw new BeanInstantiationException("There's more that 1 bean registered for the " + clazz);
        }
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

    private boolean isSystemBeanPresent(Class<?> systemClassForSearch) {
        for (Iterator<BeanDefinition> iterator = beanDefinitions.iterator(); iterator.hasNext(); ) {
            try {
                BeanDefinition beanDefinition = iterator.next();
                Class clazz = Class.forName(beanDefinition.getClassName());
                if (systemClassForSearch.isAssignableFrom(clazz)) {
                    if (systemClassForSearch == BeanFactoryPostProcessor.class) {
                        BeanFactoryPostProcessor instance = (BeanFactoryPostProcessor) clazz.newInstance();
                        beanFactoryPostProcessors.add(instance);
                    } else {
                        BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                        beanPostProcessors.add(instance);
                    }
                    iterator.remove();
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return beanFactoryPostProcessors.size() != 0 || beanPostProcessors.size() != 0;
    }

    private void postProcessBeanFactory() {
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessors) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanDefinitions);
        }
    }

    private void postProcessBefore() {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            for (Bean bean : beans) {
                beanPostProcessor.postProcessBeforeInitialization(bean.getValue(), bean.getId());
            }
        }
    }

    private void postProcessAfter() {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            for (Bean bean : beans) {
                beanPostProcessor.postProcessAfterInitialization(bean.getValue(), bean.getId());
            }
        }
    }

    private void initMethod() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (null != beanDefinition.getInitMethod()) {
                Object bean = getBean(beanDefinition.getId());
                Class<?> clazz = bean.getClass();
                try {
                    Method method = clazz.getMethod(beanDefinition.getInitMethod());
                    method.invoke(bean);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
