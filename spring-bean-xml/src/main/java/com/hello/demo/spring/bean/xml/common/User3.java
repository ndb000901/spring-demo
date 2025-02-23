package com.hello.demo.spring.bean.xml.common;

public class User3 {

    private String name;


    public String getName() {
        System.out.println("User3 getName");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("User3 setName");
    }


    public User3(String name) {
        this.name = name;
        System.out.println("User3 有参构造函数");
    }

    public User3() {
        System.out.println("User3 无参构造函数");
    }

    @Override
    public String toString() {
        return "User3{" +
                "name='" + name + '\'' +
                '}';
    }

    public void initMethod() {
        System.out.println("init method run...");
    }

    public void destroyMethod() {
        System.out.println("destroy method run...");
    }
}
