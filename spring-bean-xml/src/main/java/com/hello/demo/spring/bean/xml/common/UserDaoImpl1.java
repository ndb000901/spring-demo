package com.hello.demo.spring.bean.xml.common;

public class UserDaoImpl1 implements UserDao {
    @Override
    public User3 getUser() {
        System.out.println("UserDaoImpl.getUser run....");
        User3 user = new User3();
        user.setName("UserDaoImpl1");
        return user;
    }
}
