package com.comeup.design.builder;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Computer {

    private String screen;
    private Integer id;
    private String keyboard;
    private String mouse;

    private Computer() {
    }

    @Override
    public String toString() {
        return "Computer{" +
                "screen='" + screen + '\'' +
                ", id=" + id +
                ", keyboard='" + keyboard + '\'' +
                ", mouse='" + mouse + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Computer computer = new ComputerBuilder().buildId(12).buildKeyboard("151").buildScreen("151").buildMouse("asf").build();
        System.out.println(computer);
    }

    public static class ComputerBuilder {

        private final Computer computer = new Computer();

        public ComputerBuilder buildId(Integer id) {
            computer.id = id;
            return this;
        }

        public ComputerBuilder buildScreen(String screen) {
            computer.screen = screen;
            return this;
        }

        public ComputerBuilder buildKeyboard(String keyboard) {
            computer.keyboard = keyboard;
            return this;
        }

        public ComputerBuilder buildMouse(String mouse) {
            computer.mouse = mouse;
            return this;
        }

        public Computer build() {
            return computer;
        }



    }


}
