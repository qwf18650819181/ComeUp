package com.comeup.design.abstractfactory;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public abstract class NationFactory {
    abstract Food createFood();
    abstract Weapon createWeapon();
    abstract Vehicle createVehicle();

}
