package com.rpc.zeng.consumer.proxy;


import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class ClientProxyTool implements ClientProxy {

    private ClientProxy clientProxy;

    public ClientProxyTool(ParameterSettings parameterSettings) {
        switch (parameterSettings.getClientProxy()) {
            case "RpcNettyClientCGLIBProxy":
                clientProxy = new RpcNettyClientCGLIBProxy();
                break;
            case "RpcNettyClientJDKProxy":
                clientProxy = new RpcNettyClientJDKProxy();
                break;
            default:
                try {
                    throw new RpcException("没有该代理对象");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                }
        }
    }

    @Override
    public Object getBean(Class<?> serviceClass, ParameterSettings parameterSettings) {
        return clientProxy.getBean(serviceClass, parameterSettings);
    }
}
