package com.hello.demo.spring.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileResourceDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = System.getProperties();

        FileSystemResource fileSystemResource =
                new FileSystemResource("/home/hello/code/java/spring/spring-demo/spring-resource/src/main/resources/bean.xml");
        System.out.println(fileSystemResource.getFilename());
        System.out.println(fileSystemResource.exists());

        // 3. 读取文件内容
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileSystemResource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("Hello, World!");
    }
}