package com.hello.demo.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger id;

    private String name;

    private String email;

    private Integer status;

    private String password;

    private String salt;

    private LocalDateTime createTime;

    private BigInteger createBy;

    private LocalDateTime updateTime;

    private BigInteger updateBy;
}
