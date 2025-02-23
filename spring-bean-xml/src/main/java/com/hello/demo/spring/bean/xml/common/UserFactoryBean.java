package com.hello.demo.spring.bean.xml.common;


import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User3> {

    @Override
    public User3 getObject() throws Exception {
        User3 user = new User3();
        user.setName("user1-->factoryBean");
        System.out.println("UserBeanFactory.getObject run...");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User3.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
