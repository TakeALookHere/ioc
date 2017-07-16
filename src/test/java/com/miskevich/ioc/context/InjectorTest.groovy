package com.miskevich.ioc.context

import com.miskevich.ioc.model.Bean
import com.miskevich.ioc.model.BeanDefinition
import com.miskevich.ioc.model.BeanProperty
import com.miskevich.ioc.testdata.AllDataTypesHolderVO
import com.miskevich.ioc.testdata.EmailService
import com.miskevich.ioc.testdata.PaymentService
import com.miskevich.ioc.testdata.providers.BeanAndPropertyProvider
import com.miskevich.ioc.testdata.providers.BeanDefinitionDataProvider
import org.testng.annotations.Test

import java.lang.reflect.Method

import static org.testng.Assert.assertEquals
import static org.testng.Assert.assertTrue

class InjectorTest {

    @Test(dataProvider = "provideBeanAndPropertyInt", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterInt(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setIntType', int.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getIntType(), Integer.parseInt(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyInteger", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterInteger(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setIntegerType', Integer.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getIntegerType(), Integer.parseInt(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyDouble", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterDouble(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setDoubleType', double.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getDoubleType(), Double.parseDouble(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyDoubleBig", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterDoubleBig(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setDoubleBigType', Double.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getDoubleBigType(), Double.parseDouble(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyLong", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterLong(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setLongType', long.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getLongType(), Long.parseLong(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyLongBig", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterLongBig(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setLongBigType', Long.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getLongBigType(), Long.parseLong(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyFloat", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterFloat(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setFloatType', float.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getFloatType(), Float.parseFloat(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyFloatBig", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterFloatBig(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setFloatBigType', Float.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getFloatBigType(), Float.parseFloat(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyShort", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterShort(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setShortType', short.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getShortType(), Short.parseShort(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyShortBig", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterShortBig(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setShortBigType', Short.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getShortBigType(), Short.parseShort(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyBoolean", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterBoolean(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setBooleanType', boolean.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getBooleanType(), Boolean.parseBoolean(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyBooleanBig", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterBooleanBig(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setBooleanBigType', Boolean.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getBooleanBigType(), Boolean.parseBoolean(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyByte", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterByte(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setByteType', byte.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getByteType(), Byte.parseByte(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyByteBig", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterByteBig(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setByteBigType', Byte.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getByteBigType(), Byte.parseByte(beanProperty.getValue()))
    }

    @Test(dataProvider = "provideBeanAndPropertyChar", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterChar(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setCharType', char.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getCharType(), beanProperty.getValue().charAt(0))
    }

    @Test(dataProvider = "provideBeanAndPropertyCharacter", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterCharacter(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setCharacterType', Character.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getCharacterType(), beanProperty.getValue().charAt(0))
    }

    @Test(dataProvider = "provideBeanAndPropertyString", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterString(Bean bean, BeanProperty beanProperty) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setStringType', String.class)

        Injector injector = new Injector()
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getStringType(), beanProperty.getValue())
    }

    @Test(dataProvider = "provideBeanAndPropertyRef", dataProviderClass = BeanAndPropertyProvider.class)
    void testInjectBeanPropertiesIntoSetterRef(Bean bean, BeanProperty beanProperty, List<Bean> beans) {
        Class clazz = Class.forName("com.miskevich.ioc.testdata.AllDataTypesHolderVO")
        Method method = clazz.getDeclaredMethod('setEmailService', EmailService.class)

        Injector injector = new Injector()
        injector.setBeans(beans)
        injector.injectPropertyIntoSetter(bean, beanProperty, method)
        AllDataTypesHolderVO value = (AllDataTypesHolderVO) bean.getValue()
        assertEquals(value.getEmailService(), beans.get(0).getValue())
    }

    @Test(dataProvider = "provideBeanDefinitionsInjectionCheck", dataProviderClass = BeanDefinitionDataProvider.class)
    void testInjectBeanProperties(List<BeanDefinition> beanDefinitions, List<Bean> beans) {
        Injector injector = new Injector()
        injector.setBeans(beans)
        injector.injectBeanProperties(beanDefinitions)

        for (Bean bean : beans) {
            if (bean.getId().equals('paymentWithMaxAmountService')) {
                def paymentService = (PaymentService) bean.getValue()
                assertEquals(paymentService.getMaxAmount(), 5000)
                assertTrue(paymentService.getEmailService() instanceof EmailService)
            } else {
                def emailService = (EmailService) bean.getValue()
                assertEquals(emailService.getPort(), 8888)
                assertEquals(emailService.getProtocol(), "UDP")
            }
        }
    }
}
