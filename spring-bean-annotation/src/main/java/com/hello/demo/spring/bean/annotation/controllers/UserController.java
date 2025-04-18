package com.hello.demo.spring.bean.annotation.controllers;

import com.hello.demo.spring.bean.annotation.common.User;
import com.hello.demo.spring.bean.annotation.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    private UserService userService;

    public UserController() {
    }

    @Autowired
    public void setUserService( UserService userService) {
        this.userService = userService;
    }


    public User getUser() {
        System.out.println("UserController.getUser run...");
        return this.userService.getUser();
    }
}
