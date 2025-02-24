package com.hello.demo.reflect;

import com.hello.demo.reflect.common.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructorDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 获取Class 对象
        Class<?> clazz1 = User.class;
        Class<?> clazz2 = Class.forName("com.hello.demo.reflect.common.User");
        User user = new User();
        Class<?> clazz3 = user.getClass();

        Constructor<?> constructor = clazz1.getConstructor();
        User user1 = (User)constructor.newInstance();

        // 获取字段
        // getFields 只返回public属性
        Field[] fields = clazz1.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(user1, "user1");
            }
            else if (field.getName().equals("age")) {
                field.setAccessible(true);
                field.set(user1, 20);
            }
            System.out.println(field.getName());
        }

        // 获取方法
        Method[] declaredMethods = clazz1.getDeclaredMethods();
        for (Method method : declaredMethods) {
            // 执行方法
            if (method.getName().equals("toString")) {
                System.out.println(method.invoke(user1));
            }
            else if (method.getName().equals("sayHello")) {
                method.invoke(user1);
            }
        }

        // 获取父类
        Class<?> superclass = clazz1.getSuperclass();
        System.out.println(superclass.getName());

        // 获取实现的接口
        Class<?>[] interfaces = clazz1.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

        user1.sayHello();
        System.out.println(user1);
    }
}