package com.comeup.design.decorator;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public class ShooterWithShell implements Shooter {

    private Shooter shooter;

    public ShooterWithShell(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void shoot() {
        System.out.println("ShooterWithShell before");
        shooter.shoot();
        System.out.println("ShooterWithShell after");
    }
}
