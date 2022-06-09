package com.rpc.zeng.consumer.bootstrap.nio;


import com.rpc.zeng.common.method.Customer;
import com.rpc.zeng.consumer.proxy.ClientProxyTool;

/*
    以nio为网络编程框架的消费者端启动类
 */

/**
 * @author 祝英台炸油条
 */
public class NIOConsumerBootStrap12 {
    public static Customer main(String[] args) {
        ClientProxyTool proxy = new ClientProxyTool();
        return (Customer) proxy.getBean(Customer.class,null);
    }
}
