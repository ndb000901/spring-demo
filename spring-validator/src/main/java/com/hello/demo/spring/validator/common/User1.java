package com.hello.demo.spring.validator.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class User1 {

//    @Size(min = 1, max = 64, message = "Name must be between 1 and 64 characters")
    @NotContainA
    private String name;

    @Max(value = 200, message = "Age must be less than 200")
    @Min(value = 0, message = "Age must be greater than 0")
    private int age;

    @Email
    private String email;

    @Size(min = 1, max = 16, message = "Phone number must be between 1 and 64 characters")
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
