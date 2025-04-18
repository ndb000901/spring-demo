package com.hello.demo.spring.tx.annotation.controllers;

import com.hello.demo.spring.tx.annotation.common.User;
import com.hello.demo.spring.tx.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public User getUser(long id) {
        return this.userService.getUser(id);
    }

    public void addUser(User user) {
        this.userService.addUser(user);
    }

    public void updateUser(User user) {
        this.userService.updateUser(user);
    }

    public void deleteUser(long id) {
        this.userService.deleteUser(id);
    }
}
