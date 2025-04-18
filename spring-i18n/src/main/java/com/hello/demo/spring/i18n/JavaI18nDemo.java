package com.hello.demo.spring.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class JavaI18nDemo {

    public static void main(String[] args) {

        Locale defaultLocale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", defaultLocale);
        System.out.println(resourceBundle.getString("yes_button"));

        Locale usLocale = new Locale("en", "US");
        resourceBundle = ResourceBundle.getBundle("messages", usLocale);
        System.out.println(resourceBundle.getString("yes_button"));

        Locale zhLocale = new Locale("zh", "CN");
        resourceBundle = ResourceBundle.getBundle("messages", zhLocale);
        System.out.println(resourceBundle.getString("yes_button"));
    }
}
