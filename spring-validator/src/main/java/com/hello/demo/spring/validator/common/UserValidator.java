package com.hello.demo.spring.validator.common;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        // 0 <= age <=150
        if (user.getAge() < 0 || user.getAge() > 150) {
            errors.rejectValue("age", "user.age.invalid");
        }
        // name 长度1-64字符
        else if (user.getName().isEmpty() || user.getName().length() > 64) {
            errors.rejectValue("name", "user.name.invalid())");
        }
        // email 长度1-64
        else if (user.getEmail().isEmpty() || user.getEmail().length() > 64 || !user.getEmail().contains("@")) {
            errors.rejectValue("email", "user.email.invalid");

        }
        // phoneNumber 长度1-16
        else if (user.getPhoneNumber().isEmpty() || user.getPhoneNumber().length() > 16) {
            errors.rejectValue("phoneNumber", "user.phoneNumber.invalid");
        }
    }
}
