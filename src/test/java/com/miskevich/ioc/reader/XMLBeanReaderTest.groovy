package com.miskevich.ioc.reader

import com.miskevich.ioc.testdata.providers.BeanDefinitionDataProvider
import org.testng.annotations.Test

import static org.testng.Assert.assertEquals

class XMLBeanReaderTest {

    @Test(dataProvider = "provideBeanDefinitions", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanDefinitionsByPath(expectedBeanDefinitions) {
        XMLBeanReader xmlBeanReader = new XMLBeanReader()
        def actualBeanDefinitions = xmlBeanReader.getBeanDefinitions("src/test/resources/context.xml")
        assertEquals(actualBeanDefinitions, expectedBeanDefinitions)
    }


    @Test(dataProvider = "provideBeanDefinitionsPaths", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanDefinitionsByPaths(expectedBeanDefinitions) {
        XMLBeanReader xmlBeanReader = new XMLBeanReader()
        def actualBeanDefinitions = xmlBeanReader.getBeanDefinitions("src/test/resources/context.xml", "src/test/resources/email-context.xml")
        assertEquals(actualBeanDefinitions, expectedBeanDefinitions)
    }
}
