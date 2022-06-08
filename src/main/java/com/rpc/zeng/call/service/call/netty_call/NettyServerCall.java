package com.rpc.zeng.call.service.call.netty_call;


//启动类 给定对应的端口 进行启动并监听

import com.rpc.zeng.call.service.netty_bootstrap.NettyServerBootStrap;

/**
 * @author 祝英台炸油条
 */
public class NettyServerCall {
    public static void main(String[] args) {
        NettyServerBootStrap.start();
    }
}
