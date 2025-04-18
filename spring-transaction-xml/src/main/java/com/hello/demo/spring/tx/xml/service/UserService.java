package com.hello.demo.spring.tx.xml.service;


import com.hello.demo.spring.tx.xml.common.User;

public interface UserService {

    public User getUser(long id);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(long id);

}
