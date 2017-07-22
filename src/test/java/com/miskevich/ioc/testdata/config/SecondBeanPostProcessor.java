package com.miskevich.ioc.testdata.config;

import com.miskevich.ioc.config.BeanPostProcessor;
import com.miskevich.ioc.testdata.CurrentUserService;
import com.miskevich.ioc.testdata.EmailService;
import com.miskevich.ioc.testdata.PaymentService;

public class SecondBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("emailService")){
            ((EmailService)bean).setSecondCheck(25);
        }else if(beanName.equals("userService")){
            ((CurrentUserService)bean).setSecondCheck(25);
        }else if(beanName.equals("paymentWithMaxAmountService") || beanName.equals("paymentService")){
            ((PaymentService)bean).setSecondCheck(25);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(beanName.equals("paymentWithMaxAmountService") || beanName.equals("paymentService")){
            ((PaymentService)bean).setThirdCheck("AfterInitialization for payment...");
        }
        return bean;
    }
}
