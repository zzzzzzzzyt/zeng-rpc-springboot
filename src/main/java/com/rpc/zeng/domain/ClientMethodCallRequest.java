package com.rpc.zeng.domain;

import lombok.Data;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/15 16:28
 * 用作方法的调用的请求类
 **/
@Data
public class ClientMethodCallRequest {
    String methodName;
}
