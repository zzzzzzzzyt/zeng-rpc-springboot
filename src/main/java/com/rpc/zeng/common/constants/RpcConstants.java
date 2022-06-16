package com.rpc.zeng.common.constants;


import java.util.Properties;

/**
 * @author 祝英台炸油条
 */
//RPC常数类  因为@Value无法注入静态变量所以就采用直接赋值
public class RpcConstants {
    //zookeeper服务器连接地址
    public static final String ZOOKEEPER_ADDRESS = "192.168.18.128:2181";
    //超时时间
    public static final int ZOOKEEPER_SESSION_TIMEOUT = 2000;

    public static final String API_ADDRESS = "com.rpc.zeng.provider.api.";

    public static final String NACOS_DISCOVERY_ADDRESS = "http://192.168.18.128:8848/nacos/v1/ns/instance/list?";

    //找到对应要注册的地方
    public static final Properties NACOS_PROPERTIES = new Properties();

    //serverAddr nacos的地址
    //namespace 存放的服务列表
    public static Properties propertiesInit() {
        NACOS_PROPERTIES.setProperty("serverAddr", "192.168.18.128:8848");
        NACOS_PROPERTIES.setProperty("namespace", "public");
        return NACOS_PROPERTIES;
    }
}
