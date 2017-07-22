package com.miskevich.ioc.config;

import com.miskevich.ioc.model.BeanDefinition;

import java.util.List;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(List<BeanDefinition> beanDefinitions);
}
