package com.miskevich.ioc.context

import com.miskevich.ioc.exception.BeanInstantiationException
import com.miskevich.ioc.exception.BeanNotFoundException
import com.miskevich.ioc.testdata.CurrentUserService
import com.miskevich.ioc.testdata.EmailService
import com.miskevich.ioc.testdata.PaymentService
import com.miskevich.ioc.testdata.UserService
import com.miskevich.ioc.testdata.config.EmailBeanFactoryPostProcessor
import com.miskevich.ioc.testdata.config.UserBeanFactoryPostProcessor
import com.miskevich.ioc.testdata.providers.BeanDefinitionDataProvider
import org.testng.annotations.Test

import static org.testng.Assert.*

class ClassPathApplicationContextITest {

    @Test
    void testGetBeanById() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        CurrentUserService userService = (CurrentUserService) context.getBean("userService")
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof CurrentUserService)
    }

    @Test
    void testGetBeanByIdPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        CurrentUserService userService = (CurrentUserService) context.getBean("userService")
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof CurrentUserService)
    }

    @Test
    void testGetBeanByClass() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        def userService = context.getBean(CurrentUserService.class)
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof CurrentUserService)
    }

    @Test(expectedExceptionsMessageRegExp = "There's more that 1 bean registered for the class com.miskevich.ioc.testdata.PaymentService",
            expectedExceptions = BeanInstantiationException.class)
    void testGetBeanByClassMultipleBeansRegistered() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.getBean(PaymentService.class)
    }

    @Test
    void testGetBeanByClassViaInterface() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        def userService = context.getBean(UserService.class)
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof CurrentUserService)
    }

    @Test
    void testGetBeanByClassPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        def userService = context.getBean(CurrentUserService.class)
        userService.sendEmailWithUsersCount()
        assertTrue(userService instanceof CurrentUserService)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String", expectedExceptions = BeanNotFoundException.class)
    void testGetBeanByClassNotExists() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.getBean(String.class)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String", expectedExceptions = BeanNotFoundException.class)
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

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String with id: foo", expectedExceptions = BeanNotFoundException.class)
    void testGetBeanByIdAndClassNotExists() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.getBean("foo", String.class)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered for class: class java.lang.String with id: paymentWithMaxAmountService", expectedExceptions = BeanNotFoundException.class)
    void testGetBeanByIdAndClassNotExistsPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/part-of-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean("paymentWithMaxAmountService", String.class)
    }

    @Test(dataProvider = "provideBeanNames", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanNames(List<String> expectedBeanNames) {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        def actualBeanNames = context.getBeanNames()
        assertEquals(actualBeanNames, expectedBeanNames)
    }

    @Test(dataProvider = "provideBeanNames", dataProviderClass = BeanDefinitionDataProvider.class)
    void testGetBeanNamesPaths(List<String> expectedBeanNames) {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/email-context.xml",
                "src/test/resources/part-of-context.xml")
        def actualBeanNames = context.getBeanNames()
        assertEquals(actualBeanNames, expectedBeanNames)
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered with id: userService", expectedExceptions = BeanNotFoundException.class)
    void testGetBeanByIdBeanNotRegistered() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/email-context.xml")
        context.getBean("userService")
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered with id: userServiceFoo", expectedExceptions = BeanNotFoundException.class)
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

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered: emailServiceNotExists", expectedExceptions = BeanNotFoundException.class)
    void testGetBeanByIdNoRefRegistered() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/incorrect-ref-context.xml")
        context.getBean("userServiceIncorrectRef")
    }

    @Test(expectedExceptionsMessageRegExp = "No such bean was registered: emailServiceNotExists", expectedExceptions = BeanNotFoundException.class)
    void testGetBeanByIdNoRefRegisteredPaths() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/incorrect-ref-context.xml",
                "src/test/resources/email-context.xml")
        context.getBean("userServiceIncorrectRef")
    }

    @Test
    void testPostProcessBeanFactory() {
        new ClassPathApplicationContext("src/test/resources/system-beans-context.xml")
        assertEquals(UserBeanFactoryPostProcessor.getCheck(), 25)
        assertEquals(EmailBeanFactoryPostProcessor.getCheck(), "Good day!")
    }

    @Test
    void testPostProcessInitialization() {
        def context = new ClassPathApplicationContext("src/test/resources/system-beans-context.xml")
        def userServiceBean = context.getBean(CurrentUserService.class)
        def emailServiceBean = context.getBean(EmailService.class)
        def paymentServiceBean = context.getBean("paymentService", PaymentService.class)
        def paymentWithMaxAmountServiceBean = context.getBean("paymentWithMaxAmountService", PaymentService.class)

        assertTrue(userServiceBean.firstCheck)
        assertFalse(emailServiceBean.firstCheck)
        assertFalse(paymentServiceBean.firstCheck)
        assertFalse(paymentWithMaxAmountServiceBean.firstCheck)

        assertEquals(userServiceBean.secondCheck, 25)
        assertEquals(emailServiceBean.secondCheck, 25)
        assertEquals(paymentServiceBean.secondCheck, 25)
        assertEquals(paymentWithMaxAmountServiceBean.secondCheck, 25)

        assertEquals(userServiceBean.thirdCheck, "Done for user!")
        assertEquals(emailServiceBean.thirdCheck, "Done for email!")
        assertEquals(paymentServiceBean.thirdCheck, "AfterInitialization for payment...")
        assertEquals(paymentWithMaxAmountServiceBean.thirdCheck, "AfterInitialization for payment...")
    }

    @Test
    void testInitMethod() {
        def context = new ClassPathApplicationContext("src/test/resources/system-beans-context.xml")
        def userServiceBean = context.getBean(CurrentUserService.class)
        def emailServiceBean = context.getBean(EmailService.class)
        def paymentServiceBean = context.getBean("paymentService", PaymentService.class)
        def paymentWithMaxAmountServiceBean = context.getBean("paymentWithMaxAmountService", PaymentService.class)

        assertEquals(userServiceBean.fourthCheck, "User init-method check")
        assertNull(emailServiceBean.fourthCheck)
        assertEquals(paymentServiceBean.fourthCheck, "Payment init-method check")
        assertNull(paymentWithMaxAmountServiceBean.fourthCheck)
    }
}
