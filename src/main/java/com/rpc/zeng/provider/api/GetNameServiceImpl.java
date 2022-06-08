package com.rpc.zeng.provider.api;

import com.rpc.zeng.common.entity.Person;
import com.rpc.zeng.common.entity.PersonPOJO;
import com.rpc.zeng.common.method.GetNameService;

/**
 * @author 祝英台炸油条
 */
public class GetNameServiceImpl implements GetNameService {
    @Override
    public String sayGetName(Person person) {
        return person.getName();
    }

    //protobuf
    @Override
    public String sayGetName(PersonPOJO.Person person) {
        return person.getName();
    }
}
