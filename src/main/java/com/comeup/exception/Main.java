package com.comeup.exception;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {

        Person person1 = new Person();
        person1.setAge(18);
        person1.setName("qiu wanzi");
        person1.setCreateTime(new Date());

        Person person2 = new Person();
        person2.setAge(18);
        person2.setName("qiu wanzi");

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);

        Response response = new Response(person1);
        Person person = parseObject(response, Person.class);
        System.out.println(person);
        System.out.println(JSON.toJSONString(person));

        ListResponse<Person> listResponse = new ListResponse<>(list);
        List<Person> persons = parseArray(listResponse, Person.class);
        System.out.println(persons);






    }







    private static <T> T parseObject(Response response, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(response.getResponseBody()), clazz);
    }

    private static <T> List<T> parseArray(ListResponse listResponse, Class<T> clazz) {
        return JSON.parseArray(JSON.toJSONString(listResponse.getList()), clazz);
    }

}
