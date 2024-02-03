package com.comeup.design.abstractfactory;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class ManFactory extends NationFactory {
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Weapon createWeapon() {
        return new Ak47();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}
