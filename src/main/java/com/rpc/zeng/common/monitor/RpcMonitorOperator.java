package com.rpc.zeng.common.monitor;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.common.monitor.service.RpcMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/3 19:47
 **/
@Slf4j
@Component
public class RpcMonitorOperator {

    @Autowired
    private RpcMonitorService rpcMonitorService;

    public static RpcMonitorOperator rpcMonitorOperator;

    @PostConstruct
    public void init() {
        rpcMonitorOperator = this;
    }

    /**
     * 作用 就是每次开启服务提供商的时候 删除掉所有的对应项目
     */
    public void deleteAll() {
        rpcMonitorOperator.rpcMonitorService.remove(null);
    }

    /**
     * 每次开启服务的时候 就进行添加对应的服务 当然 起始调用次数为0
     */
    public void addServer(RpcMonitor rpcMonitorServer) {
        rpcMonitorOperator.rpcMonitorService.save(rpcMonitorServer);
    }

    /**
     * 更新相应的服务  将相应的服务调用次数+1  同时update的时候  会自动的更改调用时间  做这个的时候 要上锁 防止线程冲突
     */
    public synchronized void updateServer(String methodAddress) {
        QueryWrapper<RpcMonitor> wrapper = new QueryWrapper<>();
        // 没问题 查询的时候 是跟对应的列名进行比较
        wrapper.eq("method_name", methodAddress);
        RpcMonitor rpcMonitor = rpcMonitorOperator.rpcMonitorService.getOne(wrapper);
        if (rpcMonitor == null) try {
            throw new RpcException("监控中心出现错误");
        } catch (RpcException e) {
            log.error(e.getMessage(), e);
            return;
        }
        rpcMonitor.setCallNum(rpcMonitor.getCallNum() + 1);
        rpcMonitor.setCallTime(null);
        rpcMonitorOperator.rpcMonitorService.update(rpcMonitor, new QueryWrapper<RpcMonitor>().eq("id", rpcMonitor.getId()));
    }
}
