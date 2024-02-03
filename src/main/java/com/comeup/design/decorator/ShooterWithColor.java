package com.comeup.design.decorator;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public class ShooterWithColor implements Shooter {

    private Shooter shooter;

    public ShooterWithColor(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void shoot() {
        System.out.println("ShooterWithColor before");
        shooter.shoot();
        System.out.println("ShooterWithColor after");
    }
}
