package com.hello.demo.spring.validator;

import com.hello.demo.spring.validator.config.AppConfig;
import com.hello.demo.spring.validator.service.User2Service;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NotContainADemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        User2Service user2Service = context.getBean(User2Service.class);
        System.out.println(user2Service.getUser2());
    }
}
