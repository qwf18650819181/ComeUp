package com.comeup.design.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
@Slf4j
public abstract class Tea {

    protected TeaFeature teaFeature;

    public Tea(TeaFeature teaFeature)
    {
        this.teaFeature = teaFeature;
    }
    public abstract String getTeaSize();

    public void makeTea() {
        log.info("this is a {} {}", getTeaSize(), teaFeature.getTeaFeature());
    }

    public static void main(String[] args) {
        Tea tea = new LargeTea(new TeaFeatureRed());
        Tea tea1 = new LargeTea(new TeaFeatureGreen());
        Tea tea2 = new SmallTea(new TeaFeatureGreen());
        Tea tea3 = new SmallTea(new TeaFeatureRed());
        tea.makeTea();
        tea1.makeTea();
        tea2.makeTea();
        tea3.makeTea();
    }


}
