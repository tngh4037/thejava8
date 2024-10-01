package com.study.java8.section2.defaultandstatic;

public interface Foo {

    void printName();

    String getName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() { // 해당 인터페이스를 구현한 모든 구현체에 공통적으로 제공하고 싶은 기능이 생겼다.
        System.out.println(getName().toUpperCase());
    }

    // static 메서드
    // 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이스에 static 메소드를 제공할 수 있다.
    static void printAnything() {
        System.out.println("Foo");
    }

}

// [ 기본 메소드 (Default Methods) ]
// - 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
// - 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
//
// [ default 메서드 주의 ]
// - 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
// - 항상 제대로, 정상적으로 동작될거라는 보장이 없다. ( 위에서 getName()에 null이 오면 NPE ) -> 컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
// - 따라서 이런 불상사를 방지하려면, 최소한의 노력으로, 문서화를 잘해야 한다. ( @implSpec 자바독 태그 사용 => 내가 제공하는 기본 구현체가 어떤 일을 하는것인지 명시 )
//
// [ default 메서드 제약사항 ]
// - 제약사항) Object 에서 제공하는 기능(equals, hashcode, ..)은 default 메서드로 제공할 수 없다. 구현체가 직접 재정의해야 한다.
//    ㄴ ex. default boolean equals(Object obj) { return true; } // compile error
//
// [ default 메서드 참고 ]
// - 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
// - 인터페이스 구현체가 default 메서드를 재정의 할 수도 있다.
// - 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 제공할 수 있다. ( 외부 라이브러리에 있는 인터페이스에 default 메서드를 정의할 수 없다. )
