package com.rpc.zeng.controller;

import com.rpc.zeng.call.service.ClientCall;
import com.rpc.zeng.call.service.ServerCall;
import com.rpc.zeng.domain.ParameterSettings;
import com.rpc.zeng.domain.ParameterSettingsRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/8 13:45
 **/
@RestController
@RequestMapping()
public class RpcController {

    static ParameterSettings parameterSettings = new ParameterSettings();

    //先设置默认值 如果前方请求不为空就进行改变
    static {
        //设置默认值  如果传来的参数不为空的话  设置为传来的值
        parameterSettings.setVersion("2.4");
        parameterSettings.setRpcTool("Netty");
        parameterSettings.setCompressTool("DeflaterUtils");
        parameterSettings.setRegistryName("zookeeper");
        parameterSettings.setRpcSerialization("fastjson");
        parameterSettings.setClientProxy("RpcNettyClientCGLIBProxy");
        parameterSettings.setIsCompress("1");
        parameterSettings.setLoadBalanceMethod("RandomLoadBalance");
    }


    /**
     * 参数设置
     */
    @PostMapping("/parameterSettings")
    public void parameterSettings(@RequestBody ParameterSettingsRequest parameterSettingsRequest) {
        if (!StringUtils.isBlank(parameterSettingsRequest.getVersion())) {
            parameterSettings.setVersion(parameterSettingsRequest.getVersion());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getRpcTool())) {
            parameterSettings.setRpcTool(parameterSettingsRequest.getRpcTool());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getCompressTool())) {
            parameterSettings.setCompressTool(parameterSettingsRequest.getCompressTool());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getRegistryName())) {
            parameterSettings.setRegistryName(parameterSettingsRequest.getRegistryName());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getRpcSerialization())) {
            parameterSettings.setRpcSerialization(parameterSettingsRequest.getRpcSerialization());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getClientProxy())) {
            parameterSettings.setClientProxy(parameterSettingsRequest.getClientProxy());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getIsCompress())) {
            parameterSettings.setIsCompress(parameterSettingsRequest.getIsCompress());
        }
        if (!StringUtils.isBlank(parameterSettingsRequest.getLoadBalanceMethod())) {
            parameterSettings.setLoadBalanceMethod(parameterSettingsRequest.getLoadBalanceMethod());
        }


    }

    /**
     * 服务提供方controller调用    将对应的参数传入   还要带入 要生成什么  也是个request
     */
    @GetMapping("/provider")
    public void providerNetty() {
        ServerCall.main(parameterSettings);
    }


    /**
     * 消费者controller调用    将对应的参数传入   要调用什么
     */
    @GetMapping("/consumer")
    public void consumerNetty() {
        ClientCall.main(parameterSettings);
    }

}