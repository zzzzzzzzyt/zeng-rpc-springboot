package com.rpc.zeng.common.method;

import com.rpc.zeng.common.entity.Person;
import com.rpc.zeng.common.entity.PersonPOJO;
import entity.Person;
import entity.PersonPOJO;

//这是之后要被代理的对象 我们会实现它的方法

/**
 * @author 祝英台炸油条
 */
public interface Customer {
    String Hello(String msg);

    String Bye(String msg);

    //加个简单的先试试 传送person 获取他的姓名
    String GetName(Person person);

    //传送protoc编译过的类
    String GetName(PersonPOJO.Person personPOJO);

    Person GetPerson(Person person);

    //传送protoc编译过的类
    PersonPOJO.Person GetPerson(PersonPOJO.Person personPOJO);

}
