package com.rpc.zeng.call.service.netty_bootstrap;

import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.common.method.Customer;
import com.rpc.zeng.consumer.bootstrap.netty.NettyConsumerBootStrap20;
import com.rpc.zeng.consumer.bootstrap.netty.NettyConsumerBootStrap21;
import com.rpc.zeng.consumer.bootstrap.netty.NettyConsumerBootStrap22;
import com.rpc.zeng.consumer.bootstrap.netty.NettyConsumerBootStrap24;
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


        switch (parameterSettings.getVersion()) {
            case "2.0": //2.0就是简单实现远端调用 所以没实现太那个
                NettyConsumerBootStrap20.main(new String[]{"127.0.0.1", String.valueOf(6668)});
                return null;
            case "2.1":
                return NettyConsumerBootStrap21.main(parameterSettings);
            case "2.2":
                return NettyConsumerBootStrap22.main(parameterSettings);
            case "2.4": //这个版本各种序列化工具的使用
            case "2.5":
            case "2.6":
            case "2.7":
            case "2.8":
            case "2.9":
                return NettyConsumerBootStrap24.main(parameterSettings);
            default:
                try {
                    throw new RpcException("该版本还没出呢，你如果有想法可以私信我，或者提个pr");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                    return null;
                }
        }
    }
}
