package com.hello.demo.spring.bean.xml.common;

public class UserDaoImpl2 implements UserDao {

    @Override
    public User3 getUser() {
        System.out.println("UserDaoImpl2 getUSer run...");
        User3 user = new User3();
        user.setName("UserDaoImpl2");
        return user;
    }
}
