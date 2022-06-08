package com.rpc.zeng.common.method;

import com.rpc.zeng.common.entity.Person;
import com.rpc.zeng.common.entity.PersonPOJO;

/**
 * @author ףӢ̨ը����
 */
public interface GetNameService {
    String sayGetName(Person person);

    String sayGetName(PersonPOJO.Person person);
}
