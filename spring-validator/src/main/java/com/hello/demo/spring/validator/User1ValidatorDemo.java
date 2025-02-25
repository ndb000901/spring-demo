package com.hello.demo.spring.validator;

import com.hello.demo.spring.validator.common.User1;
import com.hello.demo.spring.validator.config.AppConfig;
import com.hello.demo.spring.validator.service.User1Service1;
import com.hello.demo.spring.validator.service.User1Service2;
import com.hello.demo.spring.validator.service.User1Service3;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class User1ValidatorDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        User1 user1 = context.getBean("user1", User1.class);
        System.out.println(user1);

        User1Service1 user1Service1 = context.getBean(User1Service1.class);
        System.out.println("result1: " + user1Service1.validaUser1ByValidator(user1));

        User1Service2 user1Service2 = context.getBean(User1Service2.class);
        System.out.println("result2: " + user1Service2.validatorUser1((user1)));

        User1Service3 user1Service3 = context.getBean(User1Service3.class);
        user1Service3.validatorUser(user1);
    }
}