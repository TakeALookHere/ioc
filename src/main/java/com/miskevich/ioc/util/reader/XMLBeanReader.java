package com.miskevich.ioc.util.reader;

import com.miskevich.ioc.data.BeanDefinition;
import com.miskevich.ioc.data.BeanProperty;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLBeanReader extends DefaultHandler implements BeanReader {

    private List<BeanDefinition> beanDefinitions;
    private List<BeanProperty> beanProperties;

    public XMLBeanReader() {
        this.beanDefinitions = new ArrayList<>();
        this.beanProperties = new ArrayList<>();
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

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("bean")) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(attributes.getValue("id"));
            beanDefinition.setClassName(attributes.getValue("class"));
            beanDefinitions.add(beanDefinition);
        } else if (qName.equalsIgnoreCase("property")) {
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
            beanProperties.add(beanProperty);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("bean")) {
            beanDefinitions.get(beanDefinitions.size() - 1).setBeanProperties(beanProperties);
            beanProperties = new ArrayList<>();
        }
    }

    private void parseBeanDefinitions(String path) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream file = new FileInputStream(path);
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
