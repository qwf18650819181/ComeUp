package com.comeup.spring.common.validate;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月13日 0013
 * @version: 1.0
 */
@Data
public class User {
    @NotBlank
    @NotNull
    private String name;
    private int age;
    private String sex;
    private String address;
    private String userAddress;
}
