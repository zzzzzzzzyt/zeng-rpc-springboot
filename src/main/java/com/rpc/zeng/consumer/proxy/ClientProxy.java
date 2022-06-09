package com.rpc.zeng.consumer.proxy;

//模板

import com.rpc.zeng.domain.ParameterSettings;

/**
 * @author 祝英台炸油条
 */
public interface ClientProxy {
    Object getBean(final Class<?> serviceClass, ParameterSettings parameterSettings);
}
