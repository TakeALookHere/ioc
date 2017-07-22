package com.miskevich.ioc.testdata.config;

import com.miskevich.ioc.config.BeanFactoryPostProcessor;
import com.miskevich.ioc.model.BeanDefinition;

import java.util.List;

public class EmailBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public static String check;
    public void postProcessBeanFactory(List<BeanDefinition> beanDefinitions) {
        check = "Good day!";
    }

    public static String getCheck() {
        return check;
    }
}
