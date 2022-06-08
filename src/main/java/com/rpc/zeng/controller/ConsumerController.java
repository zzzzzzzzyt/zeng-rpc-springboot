package com.rpc.zeng.controller;

import com.rpc.zeng.call.service.ClientCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/8 13:45
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @GetMapping("/netty")
    public void netty()
    {
        ClientCall.main(new String[]{});
    }

    @GetMapping("/nio")
    public void nio()
    {

    }
}
