package com.study.java8.util;

import java.util.function.*;

// 자바가 기본으로 제공하는 형수형 인터페이스(2) - BiFunction, Consumer, Supplier, Predicate, UnaryOperator, BinaryOperator
public class FunctionEtcMain {

    public static void main(String[] args) {

        // Funtion은 입력(T) 하나에 출력 하나였다면, BiFunction은 입력(T, U)이 두개이다.
        BiFunction<Integer, Integer, Integer> plusCalculator = (number1, number2) -> number1 + number2;
        System.out.println(plusCalculator.apply(10, 15));

        // Consumer<T>는 입력(T)이 하나에, 리턴이 없다.
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // Supplier<T>는 입력이 없고, 받아올 리턴(T)을 정의.
        Supplier<Integer> get10 = () -> 70; // 입력해주는 값이 없으므로, 인자를 주지 않는다.
        System.out.println(get10.get());

        // Predicate<T>는 입력을 받아서, true/false 를 리턴.
        Predicate<String> startsWith = (s) -> s.startsWith("kim");
        System.out.println(startsWith.test("kimk"));
        Predicate<Integer> isEven = (i) -> i % 2 == 0;
        System.out.println(isEven.test(3));

        // UnaryOperator<T>는 Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수형 인터페이스 ( 참고로 UnaryOperator는 Function을 상속받았다. 따라서 Function에서 제공하는 default 메서드(compose, andThen)를 사용할 수 있다. )
        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        plus10.apply(11);

        // BinaryOperator<T>는 BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입력값 두개를 받아 리턴하는 함수형 인터페이스 ( 입력 2개와 출력의 타입이 모두 같다라는 전제. 따라서 굳이 타입을 3번 적지 않아도 된다. )
        BinaryOperator<Integer> plusCalculator2 = (number1, number2) -> number1 + number2;
        System.out.println(plusCalculator2.apply(10, 15));

    }
}

// 참고) 위 각각의 함수형 인터페이스도 내부에 조합용 메서드들이 존재한다.

// 참고) 이 외에도 자바에서 제공하는 여러 함수형 인터페이스들이 존재한다.
// ( 위 함수형 인터페이스를 이해했다면, 나머지는 얼마든지 추론할 수 있다. )
//    ㄴ ex) BiPredicate<T, U>: 입력을 2개 받아서, true/false 를 리턴.
//    ㄴ ex) BooleanSupplier: 입력이 없고, boolean 을 리턴.
