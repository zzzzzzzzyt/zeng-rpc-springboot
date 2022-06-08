package com.rpc.zeng.common.serialization;

import com.rpc.zeng.common.annotation.RpcSerializationSelector;
import com.rpc.zeng.common.configuration.GlobalConfiguration;
import com.rpc.zeng.common.exception.RpcException;
import com.rpc.zeng.common.serialization.fst.FSTUtils;
import com.rpc.zeng.common.serialization.hessian.HessianUtils;
import com.rpc.zeng.common.serialization.json.FastJsonUtils;
import com.rpc.zeng.common.serialization.json.GsonUtils;
import com.rpc.zeng.common.serialization.json.JacksonUtils;
import com.rpc.zeng.common.serialization.kryo.KryoUtils;
import com.rpc.zeng.common.serialization.protostuff.ProtostuffUtils;
import lombok.extern.slf4j.Slf4j;

//这是一个进行统一序列化的一个工具

/**
 * @author 祝英台炸油条
 */
@Slf4j
public class SerializationTool implements Serializer {

    static String serialization = GlobalConfiguration.class.getAnnotation(RpcSerializationSelector.class).RpcSerialization();

    @Override
    public byte[] serialize(Object obj) {
        switch (serialization) {
            case "kryo":
                return new KryoUtils().serialize(obj);
            case "protostuff":
                return new ProtostuffUtils().serialize(obj);
            case "hessian":
                return new HessianUtils().serialize(obj);
            case "fst":
                return new FSTUtils().serialize(obj);
            case "jackson":
                return new JacksonUtils().serialize(obj);
            case "fastjson":
                return new FastJsonUtils().serialize(obj);
            case "gson":
                return new GsonUtils().serialize(obj);
            default:
                try {
                    throw new RpcException("你所找的序列化方法还没编写，或者可能在该版本被废弃了，你可以看看2.2版本");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                    return new byte[0];
                }
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        switch (serialization) {
            case "kryo":
                return new KryoUtils().deserialize(bytes, clazz);
            case "protostuff":
                return new ProtostuffUtils().deserialize(bytes, clazz);
            case "hessian":
                return new HessianUtils().deserialize(bytes, clazz);
            case "fst":
                return new FSTUtils().deserialize(bytes, clazz);
            case "jackson":
                return new JacksonUtils().deserialize(bytes, clazz);
            case "fastjson":
                return new FastJsonUtils().deserialize(bytes, clazz);
            case "gson":
                return new GsonUtils().deserialize(bytes, clazz);
            default:
                try {
                    throw new RpcException("你所找的序列化方法还没编写，或者可能在该版本被废弃了，你可以看看2.2版本");
                } catch (RpcException e) {
                    log.error(e.getMessage(), e);
                    return null;
                }
        }
    }
}
