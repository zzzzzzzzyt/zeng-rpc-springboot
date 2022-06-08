package com.rpc.zeng.provider.api;

import com.rpc.zeng.common.method.HelloService;


/**
 * @author 祝英台炸油条
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String saying) {
        return "Hello," + saying;
    }
}
