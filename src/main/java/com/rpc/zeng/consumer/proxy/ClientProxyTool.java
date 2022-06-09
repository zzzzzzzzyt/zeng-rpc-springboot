package com.rpc.zeng.consumer.proxy;


import com.rpc.zeng.consumer.proxy.netty.RpcNettyClientCGLIBProxy;
import com.rpc.zeng.consumer.proxy.netty.RpcNettyClientJDKProxy;
import com.rpc.zeng.domain.ParameterSettings;

/**
 * @author 祝英台炸油条
 */
public class ClientProxyTool implements ClientProxy {

    private ClientProxy clientProxy;

    public ClientProxyTool(ParameterSettings parameterSettings) {
        switch (parameterSettings.getClientProxy()){
            case "RpcNettyClientCGLIBProxy":
                clientProxy = new RpcNettyClientCGLIBProxy();
                break;
            case "RpcNettyClientJDKProxy":
                clientProxy = new RpcNettyClientJDKProxy();
                break;
        }
    }

    @Override
    public Object getBean(Class<?> serviceClass, ParameterSettings parameterSettings) {
        return clientProxy.getBean(serviceClass,parameterSettings);
    }
}
