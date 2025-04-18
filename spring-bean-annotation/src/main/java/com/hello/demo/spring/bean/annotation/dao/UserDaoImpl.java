package com.hello.demo.spring.bean.annotation.dao;

import com.hello.demo.spring.bean.annotation.common.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUser() {
        System.out.println("UserDaoImpl.getUser run...");
        return new User();
    }
}
