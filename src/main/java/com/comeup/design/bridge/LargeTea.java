package com.comeup.design.bridge;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
public class LargeTea extends Tea {
    public LargeTea(TeaFeature teaFeature) {
        super(teaFeature);
    }

    @Override
    public String getTeaSize() {
        return "大杯";
    }
}
