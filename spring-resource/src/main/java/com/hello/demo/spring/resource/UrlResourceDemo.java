package com.hello.demo.spring.resource;

import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class UrlResourceDemo {

    public static void main(String[] args) throws MalformedURLException {

        UrlResource resource = new UrlResource("https://www.baidu.com");
        System.out.println(resource.exists());

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
        )) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
