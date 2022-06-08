package com.rpc.zeng.provider.api;


import com.rpc.zeng.common.method.ByeService;

/**
 * @author 祝英台炸油条
 */
public class ByeServiceImpl implements ByeService {
    @Override
    public String sayBye(String saying) {
        return "Bye," + saying;
    }
}
