package com.hello.demo.spring.validator.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotContainAConstraintValidator implements ConstraintValidator<NotContainA, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("isValid");
        return !value.contains("A");
    }

    @Override
    public void initialize(NotContainA constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
