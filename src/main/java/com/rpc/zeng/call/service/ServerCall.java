package com.rpc.zeng.call.service;

import com.rpc.zeng.call.service.call.ChosenServerCall;
import com.rpc.zeng.common.annotation.RpcMethodCluster;
import com.rpc.zeng.common.annotation.RpcServerBootStrap;
import com.rpc.zeng.common.annotation.RpcToolsSelector;


//总服务端启动类 用户调用  注解是 注册什么方法进去
//调用的是什么版本的服务端启动方法
//方法的注册名必须和对应的方法一一对应
@RpcMethodCluster(method = {"Hello", "Bye", "GetName", "GetPerson"}, startNum = {2, 3, 3, 1})
@RpcServerBootStrap(version = "2.4")
@RpcToolsSelector(rpcTool = "Netty")
public class ServerCall {
    public static void main(String[] args) {
        ChosenServerCall.start();
    }
}
