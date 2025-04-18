package com.hello.demo.spring.validator.service;

import com.hello.demo.spring.validator.common.User1;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

// 需要配置MethodValidationPostProcessor
@Service
@Validated
public class User1Service3 {

    public void validatorUser(@Validated User1 user) {
        System.out.println(user);
    }
}
