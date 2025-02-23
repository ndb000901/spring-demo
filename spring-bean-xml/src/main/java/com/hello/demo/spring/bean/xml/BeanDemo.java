package com.hello.demo.spring.bean.xml;

import com.hello.demo.spring.bean.xml.common.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDemo {
    public static void main(String[] args) {
        // setter
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
        User user1 = (User)context.getBean("user1");
        System.out.println(user1);

        // 构造函数
        User user2 = (User)context.getBean("user2");
        System.out.println(user2);

        // p命名空间
        User user3 = (User)context.getBean("user3");
        System.out.println(user3);

        // c命名空间
        User user4 = (User)context.getBean("user4");
        System.out.println(user4);

        User user5 = (User)context.getBean("user5");
        System.out.println(user5);

        // 特殊值
        User user6 = (User)context.getBean("user6");
        System.out.println(user6);

        User user7 = (User)context.getBean("user7");
        System.out.println(user7);

        User user8 = (User)context.getBean("user8");
        System.out.println(user8);

        // 对象
        User1 user9 = (User1)context.getBean("user9");
        System.out.println(user9);

        User1 user10 = (User1)context.getBean("user10");
        System.out.println(user10);

        User1 user11 = (User1)context.getBean("user11");
        System.out.println(user11);

        // Array List Set Map
        User2 user12 = (User2)context.getBean("user12");
        System.out.println(user12);

        // util 命名空间
        User2 user13 = (User2)context.getBean("user13");
        System.out.println(user13);

        // 引入外部属性文件
        User user14 = (User)context.getBean("user14");
        System.out.println(user14);

        // 自动装配
        UserDao userDao1 = (UserDao)context.getBean("userDao");
        System.out.println(userDao1.toString());
//        UserDao userDao2 = (UserDao)context.getBean("userDaoImpl1-2");
//        System.out.println(userDao2.toString());
        UserService userService = (UserService)context.getBean("userService");
        System.out.println(userService.getUser().toString());
    }
}