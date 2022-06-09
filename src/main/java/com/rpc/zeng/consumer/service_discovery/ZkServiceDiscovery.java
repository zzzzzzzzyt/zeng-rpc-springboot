package com.rpc.zeng.consumer.service_discovery;

import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.common.loadbalance.AccessLoadBalance;
import com.rpc.zeng.common.loadbalance.ConsistentLoadBalance;
import com.rpc.zeng.common.loadbalance.LoadBalance;
import com.rpc.zeng.common.loadbalance.RandomLoadBalance;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

import static com.rpc.zeng.common.constants.RpcConstants.ZOOKEEPER_ADDRESS;
import static com.rpc.zeng.common.constants.RpcConstants.ZOOKEEPER_SESSION_TIMEOUT;

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class ZkServiceDiscovery {
    private static final ThreadLocal<ZooKeeper> zooKeeperThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return new ZooKeeper(ZOOKEEPER_ADDRESS, ZOOKEEPER_SESSION_TIMEOUT, watchedEvent -> {

            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    });


    // 根据所请求的服务地址 获取对应的远端地址
    public static String getMethodAddress(String methodName, ParameterSettings parameterSettings) {
        //获取对应线程中的zookeeper
        ZooKeeper zooKeeper = zooKeeperThreadLocal.get();

        try {
            //判断节点中是否存在对应路径  不存在则抛出异常
            if (zooKeeper.exists("/service/" + methodName, null) == null) {
                throw new RpcException("不存在该方法");
            }
            String prePath = "/service/" + methodName;
            //v1.5修改使用负载均衡策略 根据接口上注解选择的实现类进行调用
            LoadBalance nowLoadBalance;
            switch (parameterSettings.getLoadBalanceMethod()) {
                case "RandomLoadBalance":
                    nowLoadBalance = new RandomLoadBalance();
                    break;
                case "ConsistentLoadBalance":
                    nowLoadBalance = new ConsistentLoadBalance();
                    break;
                default:
                    nowLoadBalance = new AccessLoadBalance();
            }
            return nowLoadBalance.loadBalance(zooKeeper, prePath);
        } catch (KeeperException | InterruptedException | RpcException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


}
