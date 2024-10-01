package com.study.java8;

public class Foo {

    public static void main(String[] args) {
        // 함수형 인터페이스의 구현체를 만들어서 사용하고자 할 때, java8 이전에는 아래와 같이 만들어서 사용했다. ( 익명 내부 클래스 )
        // 1) 익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
                System.out.println("Anonymous");
            }
        };

        // 2) java8 부터 함수형 인터페이스를 구현할 때, 이를 줄여서 사용할 수 있는 문법이 생겼다. => 람다 표현식
        RunSomething runSomething1 = () -> System.out.println("Hello"); // 한줄의 경우 {} 제거 가능
        runSomething1.doIt();

        RunSomething runSomething2 = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };
        runSomething2.doIt();
    }
}

// 자바에서는 편의를 위해 기본적으로 자주 사용될 법한 함수형 인터페이스를 제공한다. ( 따라서 제공된 기능에서 활용가능한 경우, 매번 굳이 함수형 인터페이스를 직접 정의하지 않아도 된다. )
// : Function, BiFunction, Supplier, Consumer, Predicate, UnaryOperator, BinaryOperator
