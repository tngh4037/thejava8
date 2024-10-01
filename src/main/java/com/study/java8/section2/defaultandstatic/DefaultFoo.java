package com.study.java8.section2.defaultandstatic;

public class DefaultFoo implements Foo {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // default 메서드 재정의 가능
    @Override
    public void printNameUpperCase() {
        // Foo.super.printNameUpperCase();
        System.out.println(this.name.toUpperCase());
    }
}
