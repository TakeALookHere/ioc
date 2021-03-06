package com.miskevich.ioc.reader;

import com.miskevich.ioc.model.BeanDefinition;
import com.miskevich.ioc.model.BeanProperty;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLBeanReader extends DefaultHandler implements BeanReader {

    private List<BeanDefinition> beanDefinitions;
    private BeanDefinition currentBeanDefinition;
    private Map<String, Integer> classNameToCount;

    public XMLBeanReader() {
        this.beanDefinitions = new ArrayList<>();
        this.classNameToCount = new HashMap<>();
    }

    public List<BeanDefinition> getBeanDefinitions(String path) {
        parseBeanDefinitions(path);
        return beanDefinitions;
    }

    public List<BeanDefinition> getBeanDefinitions(String[] paths) {
        for (String path : paths) {
            parseBeanDefinitions(path);
        }
        return beanDefinitions;
    }

    public Map<String, Integer> getClassNameToCount() {
        return classNameToCount;
    }

    public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) throws SAXException {
        if (qualifiedName.equalsIgnoreCase("bean")) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(attributes.getValue("id"));
            String className = attributes.getValue("class");
            beanDefinition.setClassName(className);

            populateClassNameToCountMap(className);

            beanDefinition.setInitMethod(attributes.getValue("init-method"));
            beanDefinitions.add(beanDefinition);
            currentBeanDefinition = beanDefinition;
        } else if (qualifiedName.equalsIgnoreCase("property")) {
            BeanProperty beanProperty = new BeanProperty();
            beanProperty.setName(attributes.getValue("name"));

            String value = attributes.getValue("value");
            if (null != value) {
                beanProperty.setValue(value);
            }

            String ref = attributes.getValue("ref");
            if (null != ref) {
                beanProperty.setRef(ref);
            }

            if (null == currentBeanDefinition.getBeanProperties()) {
                currentBeanDefinition.setBeanProperties(new ArrayList<>());
            }
            currentBeanDefinition.getBeanProperties().add(beanProperty);
        }
    }

    private void populateClassNameToCountMap(String className) {
        if (!classNameToCount.containsKey(className)) {
            classNameToCount.put(className, 1);
        } else {
            int counter = classNameToCount.get(className);
            classNameToCount.put(className, counter + 1);
        }
    }

    private void parseBeanDefinitions(String path) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream file = new BufferedInputStream(new FileInputStream(path));
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
