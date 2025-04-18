package com.hello.demo.spring.jdbc.service;

import com.hello.demo.spring.jdbc.common.User;

public interface UserService {

    public User getUser(long id);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(long id);

}
