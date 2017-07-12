package com.miskevich.ioc.util.reader

import com.miskevich.ioc.testdata.providers.BeanDefinitionDataProvider
import org.testng.annotations.Test

import static org.testng.Assert.assertEquals


class XMLBeanReaderTest extends GroovyTestCase {
    XMLBeanReader xmlBeanReader = new XMLBeanReader()

    @Test(dataProvider = "provideBeanDefinitions", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanDefinitionsByPath(expectedBeanDefinitions) {
        def actualBeanDefinitions = xmlBeanReader.getBeanDefinitions("src/test/resources/context.xml")
        assertEquals(actualBeanDefinitions, expectedBeanDefinitions)
    }


    void testGetBeanDefinitionsByPaths() {

    }
}
