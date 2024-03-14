package com.comeup.exception;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: qiu wanzi
 * @date: 2024年3月7日 0007
 * @version: 1.0
 * @description: TODO
 */
@Data
public class Person {
    @JSONField(name = "Name")
    private String name;
    private int age;
    private String sex;
    private String phone;
    private String email;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    private String idCard;
    private String bankCard;
    private String bankName;
    private String bankAccount;
    private String bankAddress;
    private String bankPhone;
    private String bankEmail;
}
