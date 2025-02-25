package com.hello.demo.spring.validator.service;

import com.hello.demo.spring.validator.common.User1;
import com.hello.demo.spring.validator.common.User2;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class User2Service {

    @Autowired
    private User2 user2;

    @Autowired
    private Validator validator;

    public boolean getUser2() {
        Set<ConstraintViolation<User2>> sets = this.validator.validate(this.user2);
        System.out.println(sets);
        System.out.println(this.user2);
        return !sets.isEmpty();

    }
}
