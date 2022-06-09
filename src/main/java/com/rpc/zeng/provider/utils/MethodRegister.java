package com.rpc.zeng.provider.utils;

import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.domain.ParameterSettings;
import com.rpc.zeng.provider.service_registry.NacosServiceRegistry;
import com.rpc.zeng.provider.service_registry.ZkCuratorRegistry;
import com.rpc.zeng.provider.service_registry.ZkServiceRegistry;
import lombok.extern.slf4j.Slf4j;


//直接实现启动类根据启动类接口上的注解选择对应需要选取的方法

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class MethodRegister {
    /**
     * 实际进行注册的方法
     *
     * @param method            方法名字
     * @param ip                对应的ip
     * @param port              对应的port
     * @param parameterSettings 参数
     */
    public static void register(String method, String ip, int port, ParameterSettings parameterSettings) {


        switch (parameterSettings.getRegistryName()) {
            case "nacos":
                NacosServiceRegistry.registerMethod(method, ip, port);
                break;
            case "zookeeper":
                ZkServiceRegistry.registerMethod(method, ip, port);
                break;
            case "zkCurator":
                ZkCuratorRegistry.registerMethod(method, ip, port);
                break;
            default:
                try {
                    throw new RpcException("不存在该注册中心");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                }
        }
    }
}
