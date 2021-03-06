package com.rpc.zeng.consumer.service_discovery;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.rpc.zeng.common.constants.RpcConstants;
import com.rpc.zeng.common.exception.RpcException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Properties;

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class NacosServiceDiscovery {
    public static String getMethodAddress(String methodName) {
        Properties properties = RpcConstants.propertiesInit();
        Instance instance = null;
        try {
            NamingService namingService = NacosFactory.createNamingService(properties);

            //这个方法内部实现了负载均衡
            instance = namingService.selectOneHealthyInstance(methodName);
        } catch (NacosException e) {
            log.error(e.getMessage(), e);
        }
        if (Objects.isNull(instance)) {
            log.info("没有提供该方法");
            try {
                throw new RpcException("没有对应的方法");
            } catch (RpcException e) {
                log.error(e.getMessage(), e);
            }
        }
        //为空的化 就抛出异常
        assert instance != null;
        String ip = instance.getIp();
        int port = instance.getPort();
        return ip + ":" + port;
    }

}
