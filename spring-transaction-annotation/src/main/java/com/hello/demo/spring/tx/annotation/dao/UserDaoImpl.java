package com.hello.demo.spring.tx.annotation.dao;

import com.hello.demo.spring.tx.annotation.common.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User getUser(long id) {
        String sql = "select * from user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),id);
        logger.info(user);
        return user;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user(id, name, age, email) values(?, ?, ?, ?)";

        int result = this.jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAge(), user.getEmail());

        logger.info(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User user = this.getUser(id);
        String sql = "delete from user where id = ?";
        int result = this.jdbcTemplate.update(sql, id);
        String insertSql = "insert into user_bak(id, name, age, email) values(?, ?, ?, ?)";
        int insertResult = this.jdbcTemplate.update(insertSql, user.getId(), user.getName(), user.getAge(), user.getEmail());
        logger.info(result + String.valueOf(insertResult));
    }

    @Override
    public void updateUser(User user) {
        String sql = "update user set name = ?, age = ?, email = ? where id = ?";
        int result = this.jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getEmail(), user.getId());

        logger.info(user);
    }
}
