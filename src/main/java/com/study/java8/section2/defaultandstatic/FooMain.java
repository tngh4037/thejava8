package com.study.java8.section2.defaultandstatic;

public class FooMain {

    public static void main(String[] args) {
        Foo foo = new DefaultFoo("kim");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();
    }
}
