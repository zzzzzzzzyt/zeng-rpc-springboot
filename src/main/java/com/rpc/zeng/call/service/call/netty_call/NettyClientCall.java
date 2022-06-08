package com.rpc.zeng.call.service.call.netty_call;


//客户端启动类

import com.rpc.zeng.call.service.netty_bootstrap.NettyClientBootStrap;
import com.rpc.zeng.common.method.Customer;

/**
 * @author 祝英台炸油条
 */
public class NettyClientCall {
    public static Customer main(String[] args) {
        return NettyClientBootStrap.start();
    }
}
