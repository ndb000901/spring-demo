package com.hello.demo.spring.bean.xml.common;

public class Book {

    private String bookName;

    private int price;

    public Book() {
    }


    public Book(String bookName, int price) {
        this.bookName = bookName;
        this.price = price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }
}
