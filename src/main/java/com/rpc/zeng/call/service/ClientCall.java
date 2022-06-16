package com.rpc.zeng.call.service;

import com.rpc.zeng.call.service.call.NettyClientCall;
import com.rpc.zeng.common.entity.Person;
import com.rpc.zeng.common.method.Customer;
import com.rpc.zeng.domain.ClientMethodCall;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


//总客户端启动类  用户调用 什么版本的  和用什么工具 使用什么注册中心  序列化的选择 都可以用这个来玩
//注册中心不能给过去 这样就是重复依赖了

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class ClientCall {
    public static void main(ParameterSettings parameterSettings, ClientMethodCall clientMethodCall) {
        //实现调用
        Customer customer = NettyClientCall.createCustomer(parameterSettings);
        assert customer != null;
        Map<String, String> methodMap = clientMethodCall.getMethod();
        for (Map.Entry<String, String> method : methodMap.entrySet()) {
            switch (method.getKey()) {
                case "Hello":
                    log.info(customer.Hello(method.getValue()));
                    break;
                case "Bye":
                    log.info(customer.Bye(method.getValue()));
                    break;
                case "GetName":
                    log.info(customer.GetName(new Person(method.getValue())));
                    break;
                case "GetPerson":
                    Person person = customer.GetPerson(new Person(method.getValue()));
                    log.info("获取对应类" + person.getClass() + "，名字为" + person.getName());
                    break;
                default:
                    log.info("没有你申请的方法不好意思,请重新输入");
            }
        }
    }
}
