package com.hello.demo.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

public class SystemInfo implements SystemInfoMBean {
    private String systemName = "MyApplication";
    private final RuntimeMXBean runtimeMXBean;
    private final ThreadMXBean threadMXBean;
    
    public SystemInfo() {
        this.runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        this.threadMXBean = ManagementFactory.getThreadMXBean();
    }
    
    @Override
    public long getUptime() {
        return runtimeMXBean.getUptime();
    }
    
    @Override
    public String getSystemName() {
        return systemName;
    }
    
    @Override
    public void setSystemName(String name) {
        this.systemName = name;
    }
    
    @Override
    public String getSystemInfo() {
        return String.format("System: %s, Uptime: %d ms, Threads: %d", 
                           systemName, getUptime(), getThreadCount());
    }
    
    @Override
    public void gc() {
        System.gc();
        System.out.println("Garbage collection requested");
    }
    
    @Override
    public int getThreadCount() {
        return threadMXBean.getThreadCount();
    }
}