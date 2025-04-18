package com.hello.demo.spring.tx.annotation.service;

import com.hello.demo.spring.tx.annotation.common.User;
import com.hello.demo.spring.tx.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(long id) {
        return this.userDao.getUser(id);
    }

    @Override
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        this.userDao.deleteUser(id);
    }
}
