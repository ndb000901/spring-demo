package com.hello.demo.lombok;


import lombok.Cleanup;
import lombok.SneakyThrows;
import java.io.FileInputStream;

public class User {

    private String name;

    private int age;


    @SneakyThrows
    public void readBook() {
        @Cleanup
        FileInputStream inputStream = new FileInputStream("hello.txt");

    }

}
