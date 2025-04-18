package com.hello.demo.spring.aop.annotation;

import com.hello.demo.spring.aop.annotation.config.AppConfig;
import com.hello.demo.spring.aop.annotation.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.getUser();
    }
}