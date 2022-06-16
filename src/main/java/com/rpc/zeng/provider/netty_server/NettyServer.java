package com.rpc.zeng.provider.netty_server;


import com.rpc.zeng.common.codec.AddCodec;
import com.rpc.zeng.common.constants.RpcConstants;
import com.rpc.zeng.domain.ParameterSettings;
import com.rpc.zeng.provider.netty_server_handler.NettyServerHandler;
import com.rpc.zeng.provider.utils.MethodRegister;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

//进行启动 绑定然后创建对应的 然后注册进注册中心去

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class NettyServer {
    public static void start(String methodName, int port, ParameterSettings parameterSettings) {
        //真正的实现逻辑 被封装到下面的方法当中了
        start0(methodName, port, parameterSettings);
    }

    private static void start0(String methodName, int port, ParameterSettings parameterSettings) {
        //先将地址进行注册
        MethodRegister.register(methodName, "127.0.0.1", port, parameterSettings);

        //开始创建相应的netty服务端
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //创建对应的工作组 用于处理不同的事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //进行初始化
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class) //自身实现的通道
                    .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列得到的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            log.info(socketChannel.remoteAddress() + "连接上了");
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //添加的处理器 根据相应的注解而定
                            Method method = null;
                            try {
                                method = Class.forName(RpcConstants.API_ADDRESS + methodName + "ServiceImpl").getMethods()[0];
                            } catch (ClassNotFoundException e) {
                                log.error(e.getMessage(), e);
                            }
                            assert method != null;
                            AddCodec.addCodec(pipeline, method, false, parameterSettings);

                            //传入的直接是方法本身了 而不是方法名字
                            pipeline.addLast(new NettyServerHandler(methodName, parameterSettings));
                        }
                    });

            ChannelFuture channelFuture = null;
            try {
                channelFuture = serverBootstrap.bind(port).sync();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }

            //对连接结果进行监听
            assert channelFuture != null;
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                if (channelFuture1.isSuccess()) log.info("连接上" + port + "端口");
                else log.info("连接端口失败");
            });

            //等待关闭 同步
            try {
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        } finally {
            //结束了的话  就进行关闭了
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
