package com.hello.demo.reflect.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserProxy implements InvocationHandler {

    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName() + " 执行前");
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + " 执行后");
        return result;
    }
}
