package com.hello.demo.reflect;

import com.hello.demo.reflect.common.SayHello;
import com.hello.demo.reflect.common.User;
import com.hello.demo.reflect.common.UserProxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {

        SayHello user = new User();

        SayHello proxy = (SayHello)Proxy.newProxyInstance(
                user.getClass().getClassLoader(),
                user.getClass().getInterfaces(),
                new UserProxy(user)
        );
        proxy.sayHello();
    }
}
