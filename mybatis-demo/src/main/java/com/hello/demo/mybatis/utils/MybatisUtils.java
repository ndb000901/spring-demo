package com.hello.demo.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {
        try {
            if (sqlSessionFactory == null) {
                String resource = "mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }

            SqlSession sqlSession = sqlSessionFactory.openSession();
            return sqlSession;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
