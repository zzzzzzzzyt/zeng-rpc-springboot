package com.rpc.zeng.consumer.proxy;


import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.domain.ParameterSettings;

/**
 * @author 祝英台炸油条
 */
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
                    e.printStackTrace();
                }
        }
    }

    @Override
    public Object getBean(Class<?> serviceClass, ParameterSettings parameterSettings) {
        return clientProxy.getBean(serviceClass, parameterSettings);
    }
}
