package com.miskevich.ioc.testdata.providers

import com.miskevich.ioc.data.BeanDefinition
import com.miskevich.ioc.data.BeanProperty
import org.testng.annotations.DataProvider


class BeanDefinitionDataProvider {

    @DataProvider(name = "provideBeanDefinitions")
    static Object[][] provideBeanDefinitions() {
        def beanDefinitions = new ArrayList<>()
        def beanProperties1 = [new BeanProperty(name: 'protocol', value: 'POP3'), new BeanProperty(name: 'port', value: '3000')]
        BeanDefinition beanDefinition1 = new BeanDefinition(
                id: 'emailService', className: 'com.miskevich.ioc.testdata.EmailService',
                beanProperties: beanProperties1)
        beanDefinitions.add(beanDefinition1)

        def beanProperties2 = [new BeanProperty(name: 'emailService', ref: 'emailService')]
        BeanDefinition beanDefinition2 = new BeanDefinition(
                id: 'userService', className: 'com.miskevich.ioc.testdata.UserService',
                beanProperties: beanProperties2)
        beanDefinitions.add(beanDefinition2)

        def beanProperties3 = [new BeanProperty(name: 'maxAmount', value: '5000'), new BeanProperty(name: 'emailService', ref: 'emailService')]
        BeanDefinition beanDefinition3 = new BeanDefinition(
                id: 'paymentWithMaxAmountService', className: 'com.miskevich.ioc.testdata.PaymentService',
                beanProperties: beanProperties3)
        beanDefinitions.add(beanDefinition3)

        def beanProperties4 = [new BeanProperty(name: 'emailService', ref: 'emailService')]
        BeanDefinition beanDefinition4 = new BeanDefinition(
                id: 'paymentService', className: 'com.miskevich.ioc.testdata.PaymentService',
                beanProperties: beanProperties4)
        beanDefinitions.add(beanDefinition4)

        def array = new Object[1][]
        array[0] = [beanDefinitions] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanDefinitionsPaths")
    static Object[][] provideBeanDefinitionsPaths() {
        def beanDefinitions = new ArrayList<>()

        def beanProperties1 = [new BeanProperty(name: 'protocol', value: 'POP3'), new BeanProperty(name: 'port', value: '3000')]
        BeanDefinition beanDefinition1 = new BeanDefinition(
                id: 'emailService', className: 'com.miskevich.ioc.testdata.EmailService',
                beanProperties: beanProperties1)
        beanDefinitions.add(beanDefinition1)

        def beanProperties2 = [new BeanProperty(name: 'emailService', ref: 'emailService')]
        BeanDefinition beanDefinition2 = new BeanDefinition(
                id: 'userService', className: 'com.miskevich.ioc.testdata.UserService',
                beanProperties: beanProperties2)
        beanDefinitions.add(beanDefinition2)

        def beanProperties3 = [new BeanProperty(name: 'maxAmount', value: '5000'), new BeanProperty(name: 'emailService', ref: 'emailService')]
        BeanDefinition beanDefinition3 = new BeanDefinition(
                id: 'paymentWithMaxAmountService', className: 'com.miskevich.ioc.testdata.PaymentService',
                beanProperties: beanProperties3)
        beanDefinitions.add(beanDefinition3)

        def beanProperties4 = [new BeanProperty(name: 'emailService', ref: 'emailService')]
        BeanDefinition beanDefinition4 = new BeanDefinition(
                id: 'paymentService', className: 'com.miskevich.ioc.testdata.PaymentService',
                beanProperties: beanProperties4)
        beanDefinitions.add(beanDefinition4)

        def beanProperties5 = [new BeanProperty(name: 'protocol', value: 'UDP'), new BeanProperty(name: 'port', value: '8888')]
        BeanDefinition beanDefinition5 = new BeanDefinition(
                id: 'emailService', className: 'com.miskevich.ioc.testdata.EmailService',
                beanProperties: beanProperties5)
        beanDefinitions.add(beanDefinition5)

        def array = new Object[1][]
        array[0] = [beanDefinitions] as Object[]
        return array
    }
}
