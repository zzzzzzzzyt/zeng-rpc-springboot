package com.rpc.zeng.call.service.call.nio_call;


import com.rpc.zeng.call.service.nio_bootstrap.NIOClientBootStrap;
import com.rpc.zeng.common.method.Customer;

//通用启动类 将启动的逻辑藏在ClientBootStrap中
public class NIOClientCall {
    public static Customer main(String[] args) {
        return NIOClientBootStrap.start();
    }
}
