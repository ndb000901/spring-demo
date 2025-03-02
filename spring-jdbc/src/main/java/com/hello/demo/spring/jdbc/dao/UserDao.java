package com.hello.demo.spring.jdbc.dao;

import com.hello.demo.spring.jdbc.common.User;

public interface UserDao {

    User getUser(long id);

    void addUser(User user);

    void deleteUser(long id);

    void updateUser(User user);
}
