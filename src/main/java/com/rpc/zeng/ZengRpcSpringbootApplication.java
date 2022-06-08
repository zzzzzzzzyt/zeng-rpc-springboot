package com.rpc.zeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"com.rpc.zeng"})
@MapperScan("com.rpc.zeng.common.monitor.mapper")
public class ZengRpcSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZengRpcSpringbootApplication.class, args);
    }
}
