package com.miskevich.ioc.testdata.config;

import com.miskevich.ioc.config.BeanFactoryPostProcessor;
import com.miskevich.ioc.model.BeanDefinition;

import java.util.List;

public class UserBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public static int check;

    public void postProcessBeanFactory(List<BeanDefinition> beanDefinitions) {
        check = 25;
    }

    public static int getCheck() {
        return check;
    }
}
