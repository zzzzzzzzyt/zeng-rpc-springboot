package com.rpc.zeng.call.service.call;


import com.rpc.zeng.call.service.call.netty_call.NettyServerCall;
import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;


//根据获取对应的启动类注解 来选择启动方法

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class ChosenServerCall {
    public static void start(ParameterSettings parameterSettings) {
        if ("Netty".equals(parameterSettings.getRpcTool())) {
            NettyServerCall.main(parameterSettings);
        } else {
            try {
                throw new RpcException("暂时还没有该方法，博主正在努力跟进中"); //抛出异常后进行捕获
            } catch (RpcException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
