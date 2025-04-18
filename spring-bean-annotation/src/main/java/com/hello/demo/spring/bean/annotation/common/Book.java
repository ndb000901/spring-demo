package com.hello.demo.spring.bean.annotation.common;

import org.springframework.stereotype.Component;

@Component
public class Book {

    private String name;

    private int price;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
