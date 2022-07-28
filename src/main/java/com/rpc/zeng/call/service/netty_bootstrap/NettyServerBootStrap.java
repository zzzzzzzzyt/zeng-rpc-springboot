package com.rpc.zeng.call.service.netty_bootstrap;

import com.rpc.zeng.api.init.ZK;
import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.domain.ParameterSettings;
import com.rpc.zeng.domain.ServerMethodRegistry;
import com.rpc.zeng.provider.bootstrap.NettyProviderBootStrap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class NettyServerBootStrap {
    public static void start(ParameterSettings parameterSettings, ServerMethodRegistry serverMethodRegistry) {
        //先对ZK进行初始化
        ZK.init();
        //当前服务端启动器 class对象

        //获取对应的方法和个数 然后进行启动
        //1.获取对应方法 在获取对应的注解  注解中的属性
        Map<String, Integer> methodMap = serverMethodRegistry.getMap();
        int mapSize = methodMap.size();
        String[] methods = new String[mapSize];
        int[] startNums = new int[mapSize];
        int index = 0;
        for (Map.Entry<String, Integer> methodRegistryEntry : methodMap.entrySet()) {
            methods[index] = methodRegistryEntry.getKey();
            startNums[index] = methodRegistryEntry.getValue();
            ++index;
        }

        //如果不存在那就返回  或者 不一致 就抛出异常
        try {
            if (ArrayUtils.isEmpty(methods)) throw new RpcException("传入方法数为0");
        } catch (RpcException e) {
            log.error(e.getMessage(), e);
            return;
        }

        //2.需要组合在一起传过去  如果不组合分别传 我怕就是端口号会出现问题
        StringBuilder methodBuilder = new StringBuilder();
        StringBuilder numBuilder = new StringBuilder();
        for (int i = 0; i < methods.length; ++i) {
            methodBuilder.append(methods[i]);
            methodBuilder.append(",");
            numBuilder.append(startNums[i]);
            numBuilder.append(",");
        }
        methodBuilder.deleteCharAt(methodBuilder.length() - 1);
        numBuilder.deleteCharAt(numBuilder.length() - 1);

        NettyProviderBootStrap.main(new String[]{methodBuilder.toString(), numBuilder.toString()}, parameterSettings);
    }
}
