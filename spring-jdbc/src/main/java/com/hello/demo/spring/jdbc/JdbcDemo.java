package com.hello.demo.spring.jdbc;

import com.hello.demo.spring.jdbc.common.User;
import com.hello.demo.spring.jdbc.config.AppConfig;
import com.hello.demo.spring.jdbc.controllers.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserController userController = context.getBean(UserController.class);

        User user = new User();
        user.setName("user1");
        user.setAge(18);
        user.setEmail("user1@gmail.com");

        User user1 = new User();
        user1.setId(2);
        user1.setName("user2");
        user1.setAge(22);
        user1.setEmail("user2@gmail.com");

//        userController.addUser(user);
//        System.out.println(userController.getUser(1));

//        userController.updateUser(user1);
        userController.deleteUser(2);
    }

}