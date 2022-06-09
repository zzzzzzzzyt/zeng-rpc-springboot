package com.rpc.zeng.domain;

import lombok.Data;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/9 16:11
 **/
@Data
public class ParameterSettings {
    private String version;
    private String rpcTool;
    private String compressTool;
    private String registryName;
    private String rpcSerialization;
}
