package com.hello.demo.spring.bean.xml.common;

public class User {

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("User 有参构造函数");
    }

    public User() {
        System.out.println("User 无参构造函数");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
