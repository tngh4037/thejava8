package com.study.java8.section1.methodreference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

// [ 메서드 레퍼런스 (::) ]
// : 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.
// : 메서드 레퍼런스는 람다 표현식을 좀 더 간결하고 읽기 쉽게 작성하기 위한 방법이다. 람다 표현식을 사용하면 특정 기능을 수행하는 코드를 짧고 명확하게 정의할 수 있지만, 메서드 레퍼런스를 사용하면 이미 정의된 메서드를 직접 참조할 수 있으므로 코드의 가독성이 향상된다.
public class GreetingMain {

    public static void main(String[] args) {
        // [ 메소드 참조하는 방법 ]

        // 1) 스태틱 메소드 참조 = 타입::스태틱 메소드
        UnaryOperator<String> hi_format1 = (s) -> "hi " + s;
        UnaryOperator<String> hi_format2 = Greeting::hi;

        // 2) 특정 객체의 인스턴스 메소드 참조 = 객체 레퍼런스::인스턴스 메소드
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; // 참고) hello 메서드를 여기서 호출하는 게 아니다. 이 메서드를 참고하고 있는 UnaryOperator 가 만들어진 것. 아래와 같이 hello를 가지고 apply() 를 해야 호출되는 것.
        System.out.println(hello.apply("kim"));

        // 3) 임의의 객체의 인스턴스 메소드 참조 = 타입::인스턴스 메소드
        String[] names = {"kim3", "kim2", "kim1"};
        Arrays.sort(names, (new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        }));
        Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));

        // 4) 생성자 참조 = 타입::new
        Supplier<Greeting> newGreeting_format1 = () -> new Greeting();
        Supplier<Greeting> newGreeting_format2 = Greeting::new; // 참고) 이 자체만으로는 실제로 인스턴스를 만든게 아니다. 아래와 같이 get() 을 해야 만들어지는 것.
        Greeting newGreeting = newGreeting_format2.get();
        
        Function<String, Greeting> kimGreeting_format1 = (s) -> new Greeting(s);
        Function<String, Greeting> kimGreeting_format2 = Greeting::new;

        Supplier<Greeting> greeting2 = Greeting::new; // 참고) 참고로 바로 위 kimGreeting_format2 에서 Greeting::new; 와 같아보이지만, 서로 다른 생성자를 참조한다.
        Greeting name_kim = kimGreeting_format2.apply("kim");
        System.out.println(name_kim.getName()); // kim
        Greeting name_null = greeting2.get();
        System.out.println(name_null.getName()); // null
    }
}

// [ 메소드 참조하는 방법 ]
// 1) 스태틱 메소드 참조 = 타입::스태틱 메소드
// 2) 특정 객체의 인스턴스 메소드 참조 = 객체 레퍼런스::인스턴스 메소드
// 3) 임의 객체의 인스턴스 메소드 참조 = 타입::인스턴스 메소드
// 4) 생성자 참조 = 타입::new (참고. 생성자를 호출할때 리턴 값은 이 객체의 타입이다.)
// 참고1) 메소드 또는 생성자의 매개변수로 람다의 입력값을 받는다.
// 참고2) 리턴값 또는 생성한 객체는 람다의 리턴값이다.
