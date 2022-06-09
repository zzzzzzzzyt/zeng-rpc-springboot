package com.rpc.zeng.consumer.proxy;


import com.rpc.zeng.domain.ParameterSettings;

import java.util.Objects;

/**
 * @author 祝英台炸油条
 */
public class ClientProxyTool implements ClientProxy {
    @Override
    public Object getBean(Class<?> serviceClass, ParameterSettings parameterSettings) {
        return Objects.requireNonNull(SPIClientProxyUtils.getUtils()).getBean(serviceClass,parameterSettings);
    }
}
