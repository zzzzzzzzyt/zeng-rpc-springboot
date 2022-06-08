package com.rpc.zeng.common.method;


import com.rpc.zeng.common.entity.Person;
import com.rpc.zeng.common.entity.PersonPOJO;

/**
 * @author 祝英台
 */
public interface GetPersonService {
    Person sayGetPerson(Person person);

    //protobuf
    PersonPOJO.Person sayGetPerson(PersonPOJO.Person person);
}
