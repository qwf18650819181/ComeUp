package com.comeup.design.decorator;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public class Tank implements Shooter {
    @Override
    public void shoot() {
        System.out.println("tank shoot");
    }

    public static void main(String[] args) {
        Shooter shooter = new ShooterWithShell(new ShooterWithColor(new Tank()));
        shooter.shoot();
    }
}
