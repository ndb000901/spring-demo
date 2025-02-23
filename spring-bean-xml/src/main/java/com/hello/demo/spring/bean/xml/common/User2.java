package com.hello.demo.spring.bean.xml.common;

import java.util.*;

public class User2 {

    private List<Book> bookList;

    private Book[] bookArray;

    private Set<Book> bookSet;

    private Map<String, Book> bookMap;

    private Properties bookProperties;

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;

    }

    public void setBookArray(Book[] bookArray) {
        this.bookArray = bookArray;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public void setBookMap(Map<String, Book> bookMap) {
        this.bookMap = bookMap;
    }

    public void setBookProperties(Properties bookProperties) {
        this.bookProperties = bookProperties;
    }

    public User2() {
    }

    public User2(List<Book> bookList, Book[] bookArray, Set<Book> bookSet, Map<String, Book> bookMap, Properties bookProperties) {
        this.bookList = bookList;
        this.bookArray = bookArray;
        this.bookSet = bookSet;
        this.bookMap = bookMap;
        this.bookProperties = bookProperties;
    }

    @Override
    public String toString() {
        return "User2{" +
                "bookList=" + bookList +
                ", bookArray=" + Arrays.toString(bookArray) +
                ", bookSet=" + bookSet +
                ", bookMap=" + bookMap +
                ", bookProperties=" + bookProperties +
                '}';
    }
}
