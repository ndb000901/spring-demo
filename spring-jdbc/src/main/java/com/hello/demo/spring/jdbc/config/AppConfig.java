package com.hello.demo.spring.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.hello")
@PropertySource("classpath:jdbc.properties")
public class AppConfig {

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driverClassName;

    @Bean
    public DruidDataSource druidDataSource() {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(this.user);
        druidDataSource.setPassword(this.password);
        druidDataSource.setUrl(this.url);
        druidDataSource.setDriverClassName(this.driverClassName);
        return druidDataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
