package com.rpc.zeng.common.monitor.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rpc.zeng.common.monitor.RpcMonitor;
import com.rpc.zeng.common.monitor.mapper.RpcMonitorMapper;
import org.springframework.stereotype.Service;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/8 14:04
 **/
@Service
public class RpcMonitorServiceImpl extends ServiceImpl<RpcMonitorMapper, RpcMonitor>
        implements RpcMonitorService {
}
