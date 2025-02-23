package com.hello.demo.spring.bean.xml.common;

public class UserService {

    private UserDao userDao;

    public User3 getUser() {
        return this.userDao.getUser();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {
    }
}
