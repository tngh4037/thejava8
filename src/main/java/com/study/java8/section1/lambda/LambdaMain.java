package com.study.java8.section1.lambda;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaMain {

    public static void main(String[] args) {
        // 람다 표현식: (인자 리스트) -> { 바디 }
        //
        // [ 인자 리스트 ]
        // - 인자가 없을 때: ()
        // - 인자가 한개일 때: (one) 또는 one
        // - 인자가 여러개 일 때: (one, two)
        // - 참고) 인자의 타입은 생략 가능하다. 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
        //
        // [ 바디 ]
        // - 화살표 오른쪽에 함수 본문을 정의한다.
        // - 여러 줄인 경우에 { }를 사용해서 묶는다.
        // - 한 줄인 경우에 생략 가능, return도 생략 가능하다.
        Supplier<Integer> get10 = () -> 10;
        Supplier<Integer> get11 = () -> { return 11; };
        Function<Integer, Integer> add1 = (a) -> a + 1;
        Function<Integer, Integer> add2 = a -> a + 1;
        BinaryOperator<Integer> sum1 = (a, b) -> a + b;
        BinaryOperator<Integer> sum2 = (Integer a, Integer b) -> a + b; // 생략가능, 추론(infer)
    }
}
