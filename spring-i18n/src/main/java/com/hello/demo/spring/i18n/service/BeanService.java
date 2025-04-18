package com.hello.demo.spring.i18n.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class BeanService {

    @Autowired
    private MessageSource messageSource;

    public void getMessage() {
        System.out.println(this.messageSource.getMessage("yes_button", null, Locale.CHINA));
        System.out.println(this.messageSource.getMessage("yes_button", null, Locale.US));
    }

}
