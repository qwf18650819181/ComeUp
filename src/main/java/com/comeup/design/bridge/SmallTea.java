package com.comeup.design.bridge;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
public class SmallTea extends Tea {
    public SmallTea(TeaFeature teaFeature) {
        super(teaFeature);
    }

    @Override
    public String getTeaSize() {
        return "小杯";
    }
}
