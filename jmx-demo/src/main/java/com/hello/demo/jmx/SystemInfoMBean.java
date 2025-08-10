package com.hello.demo.jmx;

public interface SystemInfoMBean {
    // 属性 - 可读
    long getUptime();
    
    // 属性 - 可读写
    String getSystemName();
    void setSystemName(String name);
    
    // 操作
    String getSystemInfo();
    void gc();
    
    // 只读属性
    int getThreadCount();
}