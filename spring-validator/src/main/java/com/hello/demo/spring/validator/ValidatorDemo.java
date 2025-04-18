package com.hello.demo.spring.validator;

import com.hello.demo.spring.validator.common.User;
import com.hello.demo.spring.validator.common.UserValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class ValidatorDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(1000);
        user.setName("hello");
        user.setEmail("hello@gmail.com");
        user.setPhoneNumber("12345678");

        DataBinder dataBinder = new DataBinder(user);
        dataBinder.addValidators(new UserValidator());

        dataBinder.validate();

        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult.getAllErrors());
    }
}
