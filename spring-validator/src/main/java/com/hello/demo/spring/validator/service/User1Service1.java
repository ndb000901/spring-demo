package com.hello.demo.spring.validator.service;

import com.hello.demo.spring.validator.common.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

@Service
public class User1Service1 {

    @Autowired
    private Validator validator;

    public boolean validaUser1ByValidator(User1 user) {
        BindException bindException = new BindException(user, user.getName());
        this.validator.validate(user, bindException);
        System.out.println(bindException.getAllErrors());
        return bindException.hasErrors();
    }

}
