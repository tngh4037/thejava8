package com.study.java8.section1.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class VariableCapture {

    public static void main(String[] args) {
        VariableCapture variableCapture = new VariableCapture();
        variableCapture.run();
    }

    private void run() {
        // final int baseNumber = 10;
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11 ( baseNumber 변수가 run()의 baseNumber 변수를 가린 것 (쉐도잉) )
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // 12 ( run()의 baseNumber 가 아님 ) (쉐도잉)
            }
        };
        integerConsumer.accept(12);

        // 람다
        IntConsumer printInt = (i) -> {
        // IntConsumer printInt = (baseNumber) -> { // 1) 람다는 람다를 감싸고 있는 run()과 scope이 같다. 따라서 같은 스콥(scope) 안에 똑같은 이름의 변수를 정의할 수 없다.
           // int baseNumber = 11; // 2) 람다는 람다를 감싸고 있는 run()과 scope이 같다. 따라서 같은 스콥 안에 똑같은 이름의 변수를 정의할 수 없다.
            System.out.println(i + baseNumber);
        };
        printInt.accept(10);

        // baseNumber++; // final이 안붙어있더라도, 사실상 final(effectively final) 이어야 함. ( 변경되어선 안됨 -> 컴파일 오류 )
    }
}

// [정리]
// (로컬 클래스 / 익명 클래스 / 람다) 공통 => 로컬 변수 캡처 / effective final
// : baseNumber 를 참조할 수 있다. 단, baseNumber는 final 이거나 사실상(effectively) final 이어야 한다.  ( 사실상(effectively) final: final 이라는 키워드는 없지만, 해당 변수를 더이상 어디서도 변경하지 않는 경우를 말한다. )
//
// (로컬 클래스 / 익명 클래스) vs (람다) 차이 => 쉐도잉
// : (로컬 클래스 / 익명 클래스) = 쉐도잉이 된다.
//  ㄴ (로컬 클래스 / 익명 클래스) 내부에 선언된 변수들은, 만약 그 이름과 같은 변수가 run() 이나 VariableCapture 에 있었다면, run() 이나 VariableCapture 의 변수들은 가려진다. (쉐도잉)
//  ㄴ (로컬 클래스 / 익명 클래스) 가 쉐도잉이 가능한 이유는, 별도의 또다른 스콥이기 때문에 가능하다. ( run() 안에 또다른 scope 이다. )
// : (람다) = 쉐도잉되지 않는다.
//  ㄴ 람다는 람다를 감싸고 있는 run()과 scope이 같다. ( 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다. )
//  ㄴ 따라서 쉐도잉이 일어나지 않는다. ( 같은 스콥(scope) 안에 똑같은 이름의 변수를 정의할 수 없다. )
