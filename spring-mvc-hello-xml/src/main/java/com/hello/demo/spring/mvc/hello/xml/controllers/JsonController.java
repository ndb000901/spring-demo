package com.hello.demo.spring.mvc.hello.xml.controllers;

import com.hello.demo.spring.mvc.hello.xml.common.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/json")
public class JsonController {

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setName("hello");
        user.setPassword("admin123456");
        return user;
    }
}
