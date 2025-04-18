package com.hello.demo.spring.mvc.annotation.controllers;

import com.hello.demo.spring.mvc.annotation.common.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        User user = new User();
        user.setName("user" + id);
        user.setPassword("password" + id);
        return user;
    }

}
