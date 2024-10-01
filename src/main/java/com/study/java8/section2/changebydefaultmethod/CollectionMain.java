package com.study.java8.section2.changebydefaultmethod;

import java.util.ArrayList;
import java.util.List;

// Collection의 기본 메소드
// - stream() / parallelStream()
// - removeIf(Predicate)
// - spliterator()
public class CollectionMain {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("kim1");
        names.add("kim2");
        names.add("kim3");
        names.add("kim4");
        names.add("lee");

        // stream ( 참고. 스트림(Stream) API를 사용하면 컬렉션이나 배열의 요소들을 functional 하게 처리할 수 있다. )
        long count = names.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("K"))
                .count();
        System.out.println(count);

        // removeIf
        names.removeIf(s -> s.startsWith("k"));
        names.forEach(System.out::println);
    }
}
