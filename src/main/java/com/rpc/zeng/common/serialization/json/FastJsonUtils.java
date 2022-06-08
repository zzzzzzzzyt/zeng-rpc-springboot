package com.rpc.zeng.common.serialization.json;

import com.alibaba.fastjson.JSON;
import com.rpc.zeng.common.serialization.Serializer;
import serialization.Serializer;

//阿里巴巴旗下的进行json转换的工具

/**
 * @author 祝英台炸油条
 */
public class FastJsonUtils implements Serializer {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        //后面的序列化参数也是可选可不选
        return JSON.parseObject(bytes, clazz);
    }
}
