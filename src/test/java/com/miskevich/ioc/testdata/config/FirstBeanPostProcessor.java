package com.miskevich.ioc.testdata.config;

import com.miskevich.ioc.config.BeanPostProcessor;
import com.miskevich.ioc.testdata.CurrentUserService;
import com.miskevich.ioc.testdata.EmailService;

public class FirstBeanPostProcessor implements BeanPostProcessor {
    private int userServiceCount;

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")){
            System.out.println("userService was found");
            userServiceCount++;
            ((CurrentUserService)bean).setFirstCheck(true);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("userServiceCount: " + userServiceCount);
        if(beanName.equals("emailService")){
            ((EmailService)bean).setThirdCheck("Done for email!");
        }else if(beanName.equals("userService")){
            ((CurrentUserService)bean).setThirdCheck("Done for user!");
        }
        return bean;
    }
}
