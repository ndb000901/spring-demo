package com.hello.demo.spring.aop.xml.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void getUser() {
//        int a = 1/0;
        System.out.println("UserService.getUser run...");
    }
}
