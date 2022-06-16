package com.rpc.zeng.consumer.service_discovery;

import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.common.loadbalance.AccessLoadBalance;
import com.rpc.zeng.common.loadbalance.ConsistentLoadBalance;
import com.rpc.zeng.common.loadbalance.LoadBalance;
import com.rpc.zeng.common.loadbalance.RandomLoadBalance;
import com.rpc.zeng.domain.ParameterSettings;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper;

import static com.rpc.zeng.common.constants.RpcConstants.ZOOKEEPER_ADDRESS;

//简化zookeeper的使用  更加方便获取远端的方法的信息

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class ZkCuratorDiscovery {

    //获取对应的地址
    public static String getMethodAddress(String methodName, ParameterSettings parameterSettings) {
        //创建连接后 获取对应的地址 地址和对应的端口信息 传入即可
        //同时还要实现负载均衡
        //BackoffRetry 退避策略，决定失败后如何确定补偿值。
        //ExponentialBackOffPolicy
        //指数退避策略，需设置参数sleeper、initialInterval、
        // maxInterval和multiplier，initialInterval指定初始休眠时间，默认100毫秒，
        // maxInterval指定最大休眠时间，默认30秒，multiplier指定乘数，即下一次休眠时间为当前休眠时间*multiplier；

        CuratorFramework client = CuratorFrameworkFactory.newClient(ZOOKEEPER_ADDRESS,
                new ExponentialBackoffRetry(1000, 3));
        //需要启动  当获取完毕后需要关闭相应的客户端
        client.start();
        //首先获取的时候 要负载均衡
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = client.getZookeeperClient().getZooKeeper();
            if (zooKeeper.exists("/service/" + methodName, null) == null) {
                throw new RpcException("不存在该方法");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        String prePath = "/service/" + methodName;
        //v1.5修改使用负载均衡策略 根据接口上注解选择的实现类进行调用
        String methodAddress;
        try {
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
            methodAddress = nowLoadBalance.loadBalance(zooKeeper, prePath);
            client.close();
        } finally {
            client.close();
        }
        return methodAddress;
    }


}
