package com.hello.demo.spring.tx.xml.dao;


import com.hello.demo.spring.tx.xml.common.User;

public interface UserDao {

    User getUser(long id);

    void addUser(User user);

    void deleteUser(long id);

    void updateUser(User user);
}
