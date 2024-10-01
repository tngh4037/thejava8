package com.study.java8.section2.defaultandstatic;

public class DiamondFoo implements Foo, Bar {

    @Override
    public void printName() {
    }

    @Override
    public String getName() {
        return null;
    }

    // Foo 가 제공하는 default 메서드인 printNameUpperCase
    // Bar 가 제공하는 default 메서드인 printNameUpperCase
    // 여기서, Foo 와 Bar 인터페이스를 둘 다 구현하는 클래스가 있다면(=충돌하는 default 메서드가 있는 상황), 해당 클래스는 반드시 printNameUpperCase 를 재정의해야 한다. ( 둘 중 어떤것을 사용해야 할 지 자바는 판단할 수 없다. -> 모호성 )
    @Override
    public void printNameUpperCase() {
        Foo.super.printNameUpperCase();
        Bar.super.printNameUpperCase();
    }
}
