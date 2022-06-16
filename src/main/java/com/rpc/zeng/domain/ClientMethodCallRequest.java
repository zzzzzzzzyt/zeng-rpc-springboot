package com.rpc.zeng.domain;

import lombok.Data;

import java.util.Map;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/15 16:28
 * 用作方法的调用的请求类
 **/
@Data
public class ClientMethodCallRequest {
    private Map<String, String> method;
}
