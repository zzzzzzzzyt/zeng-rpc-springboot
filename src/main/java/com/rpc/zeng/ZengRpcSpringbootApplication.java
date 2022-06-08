package com.rpc.zeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rpc.zeng.common.monitor.mapper")
public class ZengRpcSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZengRpcSpringbootApplication.class, args);
    }
}
