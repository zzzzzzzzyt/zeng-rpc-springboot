package com.rpc.zeng.call.service;

import com.rpc.zeng.call.service.call.ChosenServerCall;
import com.rpc.zeng.domain.ParameterSettings;
import com.rpc.zeng.domain.ServerMethodRegistryRequest;


//总服务端启动类 用户调用  注解是 注册什么方法进去
//调用的是什么版本的服务端启动方法
//方法的注册名必须和对应的方法一一对应
public class ServerCall {
    public static void main(ParameterSettings parameterSettings, ServerMethodRegistryRequest serverMethodRegistryRequest) {
        ChosenServerCall.start(parameterSettings,serverMethodRegistryRequest);
    }
}
