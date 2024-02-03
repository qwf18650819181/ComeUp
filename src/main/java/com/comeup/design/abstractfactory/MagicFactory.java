package com.comeup.design.abstractfactory;

/**
 * @auth: qwf
 * @description:
 */
public class MagicFactory extends NationFactory {
    @Override
    Food createFood() {
        return new MushRoom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }
}
