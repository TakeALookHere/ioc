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
    private List<Class<?>> beanFactoryPostProcessors;
    private List<Class<?>> beanPostProcessors;

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

        boolean isBeanFactoryPostProcessor = isSystemBeanPresent(beanDefinitions, BeanFactoryPostProcessor.class);
        boolean isBeanPostProcessor = isSystemBeanPresent(beanDefinitions, BeanPostProcessor.class);

        if (isBeanFactoryPostProcessor) {
            postProcessBeanFactory();
        }

        initializeBeanObjects();

        Injector injector = new Injector();
        injector.setBeans(beans);
        injector.injectBeanProperties(beanDefinitions);

        if (isBeanPostProcessor) {
            postProcess("postProcessBeforeInitialization");
        }

        initMethod();

        if (isBeanPostProcessor) {
            postProcess("postProcessAfterInitialization");
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

    private boolean isSystemBeanPresent(List<BeanDefinition> beanDefinitions, Class<?> tClass) {
        for (Iterator<BeanDefinition> iterator = beanDefinitions.iterator(); iterator.hasNext(); ) {
            try {
                BeanDefinition beanDefinition = iterator.next();
                Class clazz = Class.forName(beanDefinition.getClassName());
                if (tClass.isAssignableFrom(clazz)) {
                    if (tClass == BeanFactoryPostProcessor.class) {
                        beanFactoryPostProcessors.add(clazz);
                    } else {
                        beanPostProcessors.add(clazz);
                    }
                    iterator.remove();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return beanFactoryPostProcessors.size() != 0 || beanPostProcessors.size() != 0;
    }

    private void postProcessBeanFactory() {
        for (Class<?> beanFactoryPostProcessor : beanFactoryPostProcessors) {
            try {
                Object instance = beanFactoryPostProcessor.newInstance();
                Method postProcessBeanFactory = beanFactoryPostProcessor.getMethod("postProcessBeanFactory", List.class);
                postProcessBeanFactory.invoke(instance, beanDefinitions);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void postProcess(String methodName) {
        for (Class<?> beanPostProcessor : beanPostProcessors) {
            try {
                Object instance = beanPostProcessor.newInstance();
                Method postProcessMethod = beanPostProcessor.getMethod(methodName, Object.class, String.class);
                for (Bean bean : beans) {
                    postProcessMethod.invoke(instance, bean.getValue(), bean.getId());
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
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
