package com.comeup.shiying;

import com.comeup.shiying.sizechart.StringConstant;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * 根据用户中心组织架构开发所属开发组对应开款部门
 * 1、Dokotoo产品开发组对应开款部门=dokotoo
 * 2、商品中心下根据下面分组对应
 * 1）DL开发组=》开款部门=B2B-DearLover
 * 2）Shewin开发组=》开款部门=B2B-Shewin
 * 3）亚马逊开发组=》开款部门=B2C
 * 4）男装开发部=》开款部门=B2C
 * 3、其他都默认为空
 */
public enum DevelopDepartmentAutoTransferEnum {

    DOKOTOO("dokotoo", Collections.singletonList("DOKOTOO品牌事业部")),
    B2B_DEARLOVER("B2B-DearLover", Collections.singletonList("DL开发组")),
    B2B_SHEWIN("B2B-Shewin", Collections.singletonList("Shewin开发组")),
    B2C("B2C", Arrays.asList("亚马逊开发组", "男装开发部"));


    private final String developDepartment;
    private final List<String> departmentNameList;

    DevelopDepartmentAutoTransferEnum(String developDepartment, List<String> departmentNameList) {
        this.developDepartment = developDepartment;
        this.departmentNameList = departmentNameList;
    }

    public static String getDevelopDepartmentByDeveloperDepartments(List<String> userDepartmentNameList) {
        if (CollectionUtils.isEmpty(userDepartmentNameList)) {
            return StringConstant.EMPTY_STRING;
        }
        for (DevelopDepartmentAutoTransferEnum autoTransferEnum : values()) {
            Optional<String> optional = userDepartmentNameList.stream().filter(autoTransferEnum.departmentNameList::contains).findFirst();
            if (optional.isPresent()) {
                return autoTransferEnum.developDepartment;
            }
        }
        return StringConstant.EMPTY_STRING;
    }

    public static void main(String[] args) {
        System.out.println(getDevelopDepartmentByDeveloperDepartments(Arrays.asList("Shewin开发组", "产品开发部", "开发部")));
    }

}
