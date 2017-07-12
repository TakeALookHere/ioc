package com.miskevich.ioc.util.reader;

import com.miskevich.ioc.data.BeanDefinition;

import java.util.List;

public interface BeanReader {
    List<BeanDefinition> getBeanDefinitions(String path);

    List<BeanDefinition> getBeanDefinitions(String[] paths);
}
