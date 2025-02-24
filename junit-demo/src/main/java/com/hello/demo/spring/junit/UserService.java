package com.hello.demo.spring.junit;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void getUser() {
        System.out.println("UserService.getUser run...");
    }
}
