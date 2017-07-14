package com.miskevich.ioc.util

import com.miskevich.ioc.testdata.PaymentService
import com.miskevich.ioc.testdata.UserService
import com.miskevich.ioc.testdata.providers.BeanDefinitionDataProvider
import org.testng.annotations.Test

class ClassPathApplicationContextTest extends GroovyTestCase {

    @Test
    void testGetBeanById() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        UserService userService = (UserService) context.getBean("userService")
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof UserService)
    }

    @Test
    void testGetBeanByIdPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        UserService userService = (UserService) context.getBean("userService")
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof UserService)
    }

    @Test
    void testGetBeanByClass() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        def userService = context.getBean(UserService.class)
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof UserService)
    }

    @Test
    void testGetBeanByClassPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        def userService = context.getBean(UserService.class)
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof UserService)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByClassNotExists() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.getBean(String.class)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByClassNotExistsPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean(String.class)
    }

    @Test
    void testGetBeanByIdAndClass() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        def paymentService = context.getBean("paymentWithMaxAmountService", PaymentService.class)
        assertTrue(paymentService instanceof PaymentService)
    }

    @Test
    void testGetBeanByIdAndClassPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        def paymentService = context.getBean("paymentWithMaxAmountService", PaymentService.class)
        assertTrue(paymentService instanceof PaymentService)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String with id: paymentWithMaxAmountService", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdAndClassNotExists() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.getBean("paymentWithMaxAmountService", String.class)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String with id: paymentWithMaxAmountService", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdAndClassNotExistsPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean("paymentWithMaxAmountService", String.class)
    }

    @Test(dataProvider = "provideBeanNames", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanNames(List<String> expectedBeanNames) {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        def actualBeanNames = context.getBeanNames()
        assertEquals(expectedBeanNames, actualBeanNames)
    }

    @Test(dataProvider = "provideBeanNames", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanNamesPaths(List<String> expectedBeanNames) {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/email-context.xml",
                "src/test/resources/part-of-context.xml")
        def actualBeanNames = context.getBeanNames()
        assertEquals(expectedBeanNames, actualBeanNames)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered with id: userService", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdBeanNotRegistered() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/email-context.xml")
        context.getBean("userService")
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered with id: userServiceFoo", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdBeanNotRegisteredPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean("userServiceFoo")
    }

    @Test(expectedExceptionsMessageRegExp = "No setter was found in class com.miskevich.ioc.testdata.UserServiceNoSetter for field emailService", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdNoSetter() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/no-setter-context.xml")
        context.getBean("userService")
    }

    @Test(expectedExceptionsMessageRegExp = "No setter was found in class com.miskevich.ioc.testdata.UserServiceNoSetter for field emailService", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdNoSetterPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/no-setter-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean("userService")
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered with ref: emailServiceNotExists", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdNoRefRegistered() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/incorrect-ref-context.xml")
        context.getBean("userServiceIncorrectRef")
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered with ref: emailServiceNotExists", expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByIdNoRefRegisteredPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/incorrect-ref-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean("userServiceIncorrectRef")
    }
}
