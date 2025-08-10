package com.hello.demo.jmx;

import javax.management.*;
import javax.management.remote.*;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

public class JMXServer {
    
    public static void main(String[] args) throws Exception {
        // 创建 MBean Server
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        
        // 创建并注册 MBean
        SystemInfo systemInfo = new SystemInfo();
        ObjectName objectName = new ObjectName("com.example:type=SystemInfo");
        server.registerMBean(systemInfo, objectName);
        
        // 创建 RMI 注册表
        LocateRegistry.createRegistry(9999);
        
        // 创建 JMX 连接器服务器
        JMXServiceURL url = new JMXServiceURL(
            "service:jmx:rmi:///jndi/rmi://192.168.43.242:9999/jmxrmi");
        JMXConnectorServer connectorServer = 
            JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
        
        // 启动连接器服务器
        connectorServer.start();
        
        System.out.println("JMX Server started at: " + url);
        System.out.println("Press Enter to stop...");
        System.in.read();
        
        connectorServer.stop();
    }
}