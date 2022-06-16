package com.rpc.zeng.domain;

import lombok.Data;

import java.util.Map;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/15 16:30
 * 用作方法的注册
 **/
@Data
public class ServerMethodRegistry {
    private Map<String, Integer> map;
}
