package com.comeup.design.chainofresponsibility.ChainValidator;

/**
 * @author: qiu wanzi
 * @date: 2024年3月7日 0007
 * @version: 1.0
 * @description: TODO
 */
public class Main {

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("name");
        person.setAge(20);
        person.setSex("nan");
        person.setPhone("13311345800");
        person.setEmail("qwf@14515.com");
        person.setAddress("xiamne");
        person.setIdCard("");
        person.setBankCard("");
        person.setBankName("");
        person.setBankAccount("");
        person.setBankAddress("");
        person.setBankPhone("");
        person.setBankEmail("");


        boolean validate = ChainValidator.newInstance(person)
                .condition(p -> p.getName() != null && !p.getName().isEmpty(), "姓名不能为空")
                .condition(p -> p.getAge() > 18, "年龄必须大于18岁")
                .doValid();

        if (validate) {
            System.out.println("success");
        }


    }
}
