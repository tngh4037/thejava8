package com.study.java8.util;

import java.util.function.Function;

// 자바가 기본으로 제공하는 형수형 인터페이스(1) - Function
public class FunctionMain {

    public static void main(String[] args) {
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        // Function<T, R>
        Function<Integer, Integer> plus10_function = (i) -> i + 10;
        System.out.println(plus10_function.apply(1));

        // 함수 조합용 메서드 (미리 정의되어 있는 함수들을 조합하는 default 메서드도 제공해준다.): compose, andThen
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(multiply2.apply(1));

        // 1) compose: 입력값이 들어오면, 입력값을 가지고 먼저 뒤에있는 함수에 적용하고, 그 결과값을 앞의 함수에 입력값으로 사용한다.
        Function<Integer, Integer> multiply2AndPlus10 = plus10_function.compose(multiply2);// = 먼저 곱한 다음 더하겠다.
        System.out.println(multiply2AndPlus10.apply(2)); // 14

        // 2) andThen: 입력값이 들어오면, 입력값을 가지고 먼저 앞에있는 함수에 적용하고, 그 결과값을 뒤에 함수에 입력값으로 사용한다.
        Function<Integer, Integer> plus10AndMultiply2 = plus10_function.andThen(multiply2);
        System.out.println(plus10AndMultiply2.apply(2)); // 24
    }

    public static void main2() {

    }
}
