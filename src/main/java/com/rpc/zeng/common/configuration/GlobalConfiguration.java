package com.rpc.zeng.common.configuration;

import com.rpc.zeng.common.annotation.CompressFunction;
import com.rpc.zeng.common.annotation.HeartBeatTool;



@HeartBeatTool(isOpenFunction = true,
        readerIdleTimeSeconds = 4,
        writerIdleTimeSeconds = 4,
        allIdleTimeSeconds = 2)
public class GlobalConfiguration {
}
