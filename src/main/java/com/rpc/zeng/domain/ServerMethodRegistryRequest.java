package com.rpc.zeng.domain;

import lombok.Data;

import java.util.Map;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/15 16:31
 * 用作方法的注册的请求类
 **/
@Data
public class ServerMethodRegistryRequest {
    Map<String,Integer> map;
}
