package com.hello.demo.spring.validator.service;

import com.hello.demo.spring.validator.common.User1;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class User1Service2 {

    @Autowired
    private Validator validator;

    public boolean validatorUser1(User1 user) {
        Set<ConstraintViolation<User1>> sets = this.validator.validate(user);
        System.out.println(sets);
        return !sets.isEmpty();
    }
}
