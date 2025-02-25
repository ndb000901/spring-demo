package com.hello.demo.spring.validator.common;

public class User2 {

    @NotContainA
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                '}';
    }
}
