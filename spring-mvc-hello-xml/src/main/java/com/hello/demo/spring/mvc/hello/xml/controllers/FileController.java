package com.hello.demo.spring.mvc.hello.xml.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    public static String rootPath = "/home/hello/code/java/spring/spring-demo/file/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No File Uploaded");
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(rootPath + fileName);
        file.transferTo(dest);
        return ResponseEntity.ok("File upload success: " + fileName);

    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(String filename) throws IOException {

        Resource resource = new FileSystemResource(rootPath + filename);
        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.getFile().length())
                .body(resource);
    }

}
