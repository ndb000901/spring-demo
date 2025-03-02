package com.hello.demo.spring.tx.xml;

import com.hello.demo.spring.tx.xml.common.User;
import com.hello.demo.spring.tx.xml.controllers.UserController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxXmlDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
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