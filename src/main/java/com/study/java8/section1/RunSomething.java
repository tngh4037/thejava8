package com.study.java8.section1;

@FunctionalInterface // 함수형 인터페이스를 정의할 일이 있다면, @FunctionalInterface 애노테이션을 붙이는 것이 좋다. ( 해당 애노테이션을 붙이게 되면, 추상메서드를 2개 이상 만들려고 하면 컴파일 오류가 발생한다. => 즉, 인터페이스를 견고하게 관리하면 좋기 때문에, 함수형 인터페이스를 정의하고자 하는 경우에는 @FunctionalInterface 를 추가해주는 것이 좋다. )
public interface RunSomething {

    void doIt();

    public static void printName() {
        System.out.println("Keesun");
    }

    public default void printAge() {
        System.out.println("40");
    }

}

// 1) 함수형 인터페이스: 추상메서드가 딱 하나만 있는 인터페이스 ( 추상메서드가 2개있다면? 안된다. )
// => 위 RunSomething 은 함수형 인터페이스일까? 맞다. 다른 형태의 여러 메서드가 있더라도, 추상메서드는 딱 하나 존재하므로, 위 인터페이스는 함수형 인터페이스이다.
//
// 참고)
// - 람다: 함수형 인터페이스의 구현체를 간편하게 만드는 방식으로, 특정 메서드를 구현하는 코드를 간단하게 표현할 수 있도록 도와준다.
//  ㄴ 람다 표현식: 람다의 문법적 형태로, 실제 코드에서 사용되는 표현식
// - 메서드 레퍼런스
//  ㄴ 메서드 레퍼런스는 Java에서 기존 메서드를 간편하게 참조하여 사용할 수 있도록 하는 문법이다.
//  ㄴ 메서드 레퍼런스는 람다 표현식의 축약된 형태로, 특정 메서드를 직접 참조하여 사용한다. ( :: 연산자를 사용하여 메서드를 참조 )
