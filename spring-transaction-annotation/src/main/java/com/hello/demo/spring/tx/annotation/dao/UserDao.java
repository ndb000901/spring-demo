package com.hello.demo.spring.tx.annotation.dao;


import com.hello.demo.spring.tx.annotation.common.User;

public interface UserDao {

    User getUser(long id);

    void addUser(User user);

    void deleteUser(long id);

    void updateUser(User user);
}
