package com.hello.demo.spring.aop.xml;

import com.hello.demo.spring.aop.xml.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.getUser();
    }
}