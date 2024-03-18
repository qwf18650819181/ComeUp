package com.comeup.function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: qiu wanzi
 * @date: 2024年3月7日 0007
 * @version: 1.0
 * @description: TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private String sex;
    private String phone;
    private String email;
    private Date createTime;
    private String idCard;
    private String bankCard;
    private String bankName;
    private String bankAccount;
    private String bankAddress;
    private String bankPhone;
    private String bankEmail;
}
