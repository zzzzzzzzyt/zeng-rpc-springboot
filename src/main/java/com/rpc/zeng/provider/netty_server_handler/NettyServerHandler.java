package com.rpc.zeng.provider.netty_server_handler;


import com.rpc.zeng.common.compress.CompressTypeTool;
import com.rpc.zeng.common.constants.RpcConstants;
import com.rpc.zeng.common.serialization.SerializationTool;
import com.rpc.zeng.domain.ParameterSettings;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;


/*
 *   在2.4版本之后我们就暂时淘汰JDK序列化和protoc编译成的类进行处理了
 * */
//实现简单的服务注册和回写

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private final String methodName;
    private final CompressTypeTool compressTool;
    private final SerializationTool serializationTool;
    private final boolean openFunction;

    //要传入对应的方法名 否则不知道 netty服务器能执行什么方法
    public NettyServerHandler(String methodName, ParameterSettings parameterSettings) {
        this.methodName = methodName;
        this.compressTool = new CompressTypeTool(parameterSettings);
        this.serializationTool = new SerializationTool(parameterSettings);
        this.openFunction = Objects.equals(parameterSettings.getIsCompress(), "1");
    }


    //实现对应的方法

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("收到来自" + ctx.channel().remoteAddress() + "的信息");
        //使用反射的方法获取对应的类 通过反射再进行执行
        Class<?> calledClass = null;
        try {
            calledClass = Class.forName(RpcConstants.API_ADDRESS + methodName + "ServiceImpl");
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        assert calledClass != null;
        Method[] methods = calledClass.getMethods();
        Method method = methods[0];

        byte[] msgByteArray = (byte[]) msg;

        //根据首先进行解压  再进行反序列化
        if (openFunction) msgByteArray = compressTool.deCompress((byte[]) msg);

        //如果传入的不是数组 就去进行反序列化
        msg = serializationTool.deserialize(msgByteArray, method.getParameterTypes()[0]);


        Object response = null;
        try {
            Object instance = calledClass.newInstance();
            response = method.invoke(instance, msg);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        //获得对应信息并进行回传

        //判断是否需要通过对应的方法进行序列化  序列化都集成了
        response = serializationTool.serialize(response);

        //进一步进行压缩  压缩方法的集成
        byte[] responseByteArray = (byte[]) response;
        log.info("before compress" + responseByteArray.length);
        if (openFunction) responseByteArray = compressTool.compress(responseByteArray);
        log.info("after compress" + responseByteArray.length);

        ctx.writeAndFlush(responseByteArray);
    }


    //出现异常的话 如何进行处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
        cause.printStackTrace();
    }
}
