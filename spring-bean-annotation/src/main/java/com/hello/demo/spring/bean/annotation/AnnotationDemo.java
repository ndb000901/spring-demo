package com.hello.demo.spring.bean.annotation;

import com.hello.demo.spring.bean.annotation.common.Book;
import com.hello.demo.spring.bean.annotation.config.AnnotationConfig;
import com.hello.demo.spring.bean.annotation.config.AppConfig;
import com.hello.demo.spring.bean.annotation.controllers.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);
        UserController userController = (UserController) context.getBean("userController", UserController.class);
        System.out.println(userController.getUser());

        Book book = (Book) context.getBean(Book.class);
        System.out.println(book);

//        AppConfig appConfig = (AppConfig) context.getBean(AppConfig.class);
//        System.out.println(appConfig.getUserController().getUser());
    }
}