package com.hello.demo.spring.resource;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClassPathResourceDemo {

    public static void main(String[] args) throws IOException {

        ClassPathResource resource = new ClassPathResource("bean.xml");

        System.out.println(resource.exists());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

    }
}
