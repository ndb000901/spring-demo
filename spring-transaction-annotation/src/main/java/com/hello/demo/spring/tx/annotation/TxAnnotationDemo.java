package com.hello.demo.spring.tx.annotation;

import com.hello.demo.spring.tx.annotation.common.User;
import com.hello.demo.spring.tx.annotation.config.AppConfig;
import com.hello.demo.spring.tx.annotation.controllers.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserController userController = context.getBean(UserController.class);

        User user = new User();
        user.setAge(101);
        user.setEmail("user-tx@gmail.com");
        user.setId(101);
        user.setName("user-tx");

        // user插入一条数据
//        userController.addUser(user);
        // 删除user表记录，将删除记录写入到user_bak表中
        userController.deleteUser(user.getId());
    }
}