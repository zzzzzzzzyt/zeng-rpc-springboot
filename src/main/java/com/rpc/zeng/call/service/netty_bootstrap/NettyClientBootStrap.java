package com.rpc.zeng.call.service.netty_bootstrap;

import com.rpc.zeng.common.method.Customer;
import com.rpc.zeng.consumer.bootstrap.NettyConsumerBootStrap;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;

//netty客户端的启动类

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class NettyClientBootStrap {
    public static Customer start(ParameterSettings parameterSettings) {
        return start0(parameterSettings);
    }

    private static Customer start0(ParameterSettings parameterSettings) {
        return NettyConsumerBootStrap.main(parameterSettings);
    }
}
