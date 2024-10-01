package com.study.java8.section2.changebydefaultmethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

// Iterable의 기본 메소드
// - forEach()
// - spliterator()
public class IterableMain {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("kim1");
        names.add("kim2");
        names.add("kim3");
        names.add("kim4");

        // forEach()
        names.forEach(System.out::println);
        System.out.println("=====================");

        // spliterator()
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit(); // 반으로 쪼갠다. ( 원래 spliterator 는 절반으로 줄고, 나머지가 spliterator1 에 담긴다. ) ( 홀수개인 경우는 2개, 1개 이런식으로 쪼개진다. )

        while (spliterator.tryAdvance(System.out::println)); // 참고) tryAdvance: 다음 요소가 없으면 false
        System.out.println("=====================");
        while (spliterator1.tryAdvance(System.out::println));
    }
}
