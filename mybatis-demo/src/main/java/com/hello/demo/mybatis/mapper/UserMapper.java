package com.hello.demo.mybatis.mapper;

import com.hello.demo.mybatis.pojo.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface UserMapper {

    User getUserById(BigInteger id);

    User getUser(BigInteger id, String email);

    List<User> getUsers();

}
