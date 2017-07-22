package com.miskevich.ioc.testdata.config;

import com.miskevich.ioc.config.BeanPostProcessor;
import com.miskevich.ioc.testdata.CurrentUserService;
import com.miskevich.ioc.testdata.EmailService;
import com.miskevich.ioc.testdata.PaymentService;

public class FirstBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")){
            ((CurrentUserService)bean).setFirstCheck(true);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(beanName.equals("emailService")){
            ((EmailService)bean).setThirdCheck("Done for email!");
        }else if(beanName.equals("userService")){
            ((CurrentUserService)bean).setThirdCheck("Done for user!");
        }
        return bean;
    }
}
