package com.miskevich.ioc.reader;

import com.miskevich.ioc.model.BeanDefinition;

import java.util.List;
import java.util.Map;

public interface BeanReader {
    List<BeanDefinition> getBeanDefinitions(String path);

    List<BeanDefinition> getBeanDefinitions(String[] paths);

    Map<String, Integer> getClassNameToCount();
}
