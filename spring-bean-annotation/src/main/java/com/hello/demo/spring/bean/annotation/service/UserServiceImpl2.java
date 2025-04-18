//package com.hello.demo.spring.bean.annotation.service;
//
//import com.hello.demo.spring.bean.annotation.common.User;
//import com.hello.demo.spring.bean.annotation.dao.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//
//@Service
//@Primary
//public class UserServiceImpl2 implements UserService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public User getUser() {
//        System.out.println("UserServiceImpl2.getUser run...");
//        return this.userDao.getUser();
//    }
//}
