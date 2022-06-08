package com.rpc.zeng.consumer.bootstrap.netty;


import com.rpc.zeng.common.method.Customer;
import com.rpc.zeng.consumer.proxy.ClientProxyTool;

/*
    以netty为网络编程框架的消费者端启动类
 */
//进行启动 提供类的方式即可

/**
 * @author 祝英台炸油条
 */
public class NettyConsumerBootStrap22 {
    public static Customer main() {
        ClientProxyTool proxy = new ClientProxyTool();
        return (Customer) proxy.getBean(Customer.class);
    }
}
