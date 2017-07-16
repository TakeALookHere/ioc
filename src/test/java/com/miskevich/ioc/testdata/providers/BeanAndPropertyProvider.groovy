package com.miskevich.ioc.testdata.providers

import com.miskevich.ioc.model.Bean
import com.miskevich.ioc.model.BeanProperty
import com.miskevich.ioc.testdata.AllDataTypesHolderVO
import com.miskevich.ioc.testdata.EmailService
import org.testng.annotations.DataProvider

class BeanAndPropertyProvider {

    @DataProvider(name = "provideBeanAndPropertyInt")
    static Object[][] provideBeanAndPropertyInt() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'intType', value: '5')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyInteger")
    static Object[][] provideBeanAndPropertyInteger() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'integerType', value: '5')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyDouble")
    static Object[][] provideBeanAndPropertyDouble() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'doubleType', value: '3.36')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyDoubleBig")
    static Object[][] provideBeanAndPropertyDoubleBig() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'doubleBigType', value: '3.36')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyLong")
    static Object[][] provideBeanAndPropertyLong() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'longType', value: '12345678910')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyLongBig")
    static Object[][] provideBeanAndPropertyLongBig() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'longBigType', value: '12345678910')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyFloat")
    static Object[][] provideBeanAndPropertyFloat() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'floatType', value: '3.36')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyFloatBig")
    static Object[][] provideBeanAndPropertyFloatBig() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'floatBigType', value: '3.36')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyShort")
    static Object[][] provideBeanAndPropertyShort() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'shortType', value: '32767')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyShortBig")
    static Object[][] provideBeanAndPropertyShortBig() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'shortBigType', value: '32767')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyBoolean")
    static Object[][] provideBeanAndPropertyBoolean() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'booleanType', value: 'true')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyBooleanBig")
    static Object[][] provideBeanAndPropertyBooleanBig() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'booleanBigType', value: 'true')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyByte")
    static Object[][] provideBeanAndPropertyByte() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'byteType', value: '127')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyByteBig")
    static Object[][] provideBeanAndPropertyByteBig() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'byteBigType', value: '127')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyChar")
    static Object[][] provideBeanAndPropertyChar() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'charType', value: 't')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyCharacter")
    static Object[][] provideBeanAndPropertyCharacter() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'characterType', value: 't')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyString")
    static Object[][] provideBeanAndPropertyString() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'stringType', value: 'foo')

        def array = new Object[1][]
        array[0] = [bean, beanProperty] as Object[]
        return array
    }

    @DataProvider(name = "provideBeanAndPropertyRef")
    static Object[][] provideBeanAndPropertyRef() {
        def bean = new Bean(id: 'allDataTypes', value: new AllDataTypesHolderVO())
        def beanProperty = new BeanProperty(name: 'emailService', ref: 'emailService')
        def beans = [new Bean(id: 'emailService', value: new EmailService())]

        def array = new Object[1][]
        array[0] = [bean, beanProperty, beans] as Object[]
        return array
    }
}
