package com.rpc.zeng.domain;

import lombok.Data;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/9 16:11
 **/
@Data
public class ParameterSettings {
    private String compressTool;
    private String registryName;
    private String rpcSerialization;
    private String clientProxy;
    private String loadBalanceMethod;
    private String isCompress;
}
