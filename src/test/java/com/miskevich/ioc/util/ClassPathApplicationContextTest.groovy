package com.miskevich.ioc.util

import com.miskevich.ioc.testdata.UserService
import org.testng.annotations.Test


class ClassPathApplicationContextTest extends GroovyTestCase {

    @Test
    void testGetBean() {
        ApplicationContext context = new ClassPathApplicationContext("src/test/resources/context.xml")
        UserService userService = (UserService) context.getBean("userService")
        userService.sendEmailWithUsersCount()
    }

    void testGetBean1() {

    }

    void testGetBean2() {

    }

    void testGetBeanNames() {

    }
}
