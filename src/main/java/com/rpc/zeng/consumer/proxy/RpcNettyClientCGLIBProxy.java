package com.rpc.zeng.consumer.proxy;

import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.common.monitor.RpcMonitorOperator;
import com.rpc.zeng.consumer.netty_client.NettyClient;
import com.rpc.zeng.consumer.service_discovery.NacosServiceDiscovery;
import com.rpc.zeng.consumer.service_discovery.ZkCuratorDiscovery;
import com.rpc.zeng.consumer.service_discovery.ZkServiceDiscovery;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//Cglib实现代理模式

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class RpcNettyClientCGLIBProxy implements ClientProxy, MethodInterceptor {

    private ParameterSettings parameterSettings;

    @Override
    public Object getBean(Class serviceClass, ParameterSettings parameterSettings) {
        this.parameterSettings = parameterSettings;
        //设置动态代理增强类
        Enhancer enhancer = new Enhancer();
        //设置代理类
        enhancer.setSuperclass(serviceClass);
        //设置对应的方法执行拦截器，方法回调
        enhancer.setCallback(this);

        return enhancer.create();
    }

    /**
     * @param obj         代理对象（增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override //自定义对应的拦截 拦截方法并执行别的任务
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) {
        String methodAddress = null;
        try {
            methodAddress = getMethodAddress(method.getName(), parameterSettings);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        assert methodAddress != null;

        //每次调用时 更新对应方法的调用次数和调用方法
        RpcMonitorOperator rpcMonitorOperator = new RpcMonitorOperator();
        rpcMonitorOperator.updateServer(methodAddress);

        String[] split = methodAddress.split(":");
        return NettyClient.callMethod(split[0], Integer.parseInt(split[1]), args[0], method, parameterSettings);
    }


    /**
     * 实际去获得对应的服务 并完成方法调用的方法
     *
     * @param methodName        根据方法名  根据添加的注册中心注解来选择相应的注册中心进行  实现负载均衡获取一个方法对应地址
     * @param parameterSettings 对应传进来的参数
     */
    private static String getMethodAddress(String methodName, ParameterSettings parameterSettings) {
        switch (parameterSettings.getRegistryName()) {
            case "nacos":
                return NacosServiceDiscovery.getMethodAddress(methodName);
            case "zookeeper":
                return ZkServiceDiscovery.getMethodAddress(methodName, parameterSettings);
            case "zkCurator":
                return ZkCuratorDiscovery.getMethodAddress(methodName, parameterSettings);
            default:
                try {
                    throw new RpcException("不存在该注册中心");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                    return null;
                }
        }
    }
}
