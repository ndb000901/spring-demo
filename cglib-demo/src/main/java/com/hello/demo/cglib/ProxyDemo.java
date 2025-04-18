package com.hello.demo.cglib;

import com.hello.demo.cglib.common.User;
import com.hello.demo.cglib.common.UserProxy;

public class ProxyDemo {
    public static void main(String[] args) {
        User user = new User();
        UserProxy userProxy = new UserProxy(user);

        User userInstance = (User)userProxy.getProxyInstance();
        userInstance.sayHello();
    }
}