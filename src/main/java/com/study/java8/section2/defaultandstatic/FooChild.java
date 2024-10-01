package com.study.java8.section2.defaultandstatic;

// 인터페이스를 상속받는 인터페이스에서 default 메서드를 다시 추상 메소드로 변경할 수 있다.
public interface FooChild extends Foo {
    void printNameUpperCase(); // FooChild 에서는 Foo 가 제공하는 기본 구현체를 제공하고 싶지 않다.
}

// 그러면, FooChild 를 구현하는 모든 인스턴스는 printNameUpperCase 를 직접 재정의 해야 한다.