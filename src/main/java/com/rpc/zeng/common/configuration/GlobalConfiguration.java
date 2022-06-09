package com.rpc.zeng.common.configuration;

import com.rpc.zeng.common.annotation.CompressFunction;
import com.rpc.zeng.common.annotation.HeartBeatTool;
import com.rpc.zeng.common.annotation.LoadBalanceMethodImpl;
import com.rpc.zeng.common.loadbalance.RandomLoadBalance;


@CompressFunction(isOpenFunction = true)
@HeartBeatTool(isOpenFunction = true,
        readerIdleTimeSeconds = 4,
        writerIdleTimeSeconds = 4,
        allIdleTimeSeconds = 2)
@LoadBalanceMethodImpl(chosenMethod = RandomLoadBalance.class)
public class GlobalConfiguration {
}
