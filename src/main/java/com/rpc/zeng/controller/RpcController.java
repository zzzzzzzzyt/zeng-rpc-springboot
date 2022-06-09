package com.rpc.zeng.controller;

import com.rpc.zeng.call.service.ClientCall;
import com.rpc.zeng.call.service.ServerCall;
import com.rpc.zeng.domain.ParameterSettingsRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/8 13:45
 **/
@RestController
@RequestMapping()
public class RpcController {



    /**
     * 参数设置
     */
    @PostMapping("/parameterSettings")
    public void parameterSettings(@RequestBody ParameterSettingsRequest parameterSettingsRequest){

    }

    /**
     * 服务提供方controller调用
     */
    @GetMapping("/provider")
    public void providerNetty() {
        ServerCall.main(new String[]{});
    }



    /**
     * 消费者controller调用
     */
    @GetMapping("/consumer")
    public void consumerNetty() {
        ClientCall.main(new String[]{});
    }


}
