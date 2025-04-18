package com.hello.demo.spring.mvc.hello.xml.common;

import org.springframework.http.HttpMethod;

public class LogData {

    private String method;

    private String path;

    private String ip;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "LogData{" +
                "method=" + method +
                ", path='" + path + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
