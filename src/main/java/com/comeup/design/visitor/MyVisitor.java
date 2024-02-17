package com.comeup.design.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
public class MyVisitor {


    public SomeThing someThing;

    @Slf4j
    public static class SomeThing {
        private String name;

        public void accept(MyVisitor myVisitor) {
            log.info("SomeThing visit " + name);
            myVisitor.someThing = this;
        }
    }
    public void visit(SomeThing someThing) {
        someThing.accept(this);
    }

    public static void main(String[] args) {
        SomeThing someThing = new SomeThing();
        someThing.name = "something name";
        MyVisitor myVisitor = new MyVisitor();
        myVisitor.visit(someThing);


    }


}
