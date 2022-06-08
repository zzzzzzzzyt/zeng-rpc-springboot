package com.rpc.zeng.provider.api;

import com.rpc.zeng.common.entity.Person;
import com.rpc.zeng.common.entity.PersonPOJO;
import com.rpc.zeng.common.method.GetPersonService;

/**
 * @author 祝英台炸油条
 */
public class GetPersonServiceImpl implements GetPersonService {
    @Override
    public Person sayGetPerson(Person person) {
        return person;
    }

    //protobuf
    @Override
    public PersonPOJO.Person sayGetPerson(PersonPOJO.Person person) {
        return person;
    }
}
