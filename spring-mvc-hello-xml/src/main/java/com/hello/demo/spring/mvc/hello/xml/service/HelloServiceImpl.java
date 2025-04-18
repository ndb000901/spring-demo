package com.hello.demo.spring.mvc.hello.xml.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("HelloServiceImpl.sayHello run...");
    }
}
