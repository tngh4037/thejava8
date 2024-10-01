package com.study.java8.section2.changebydefaultmethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Comparator의 기본 메소드 및 스태틱 메소드
// - reversed()
// - thenComparing()
// - static reverseOrder() / naturalOrder()
// - static nullsFirst() / nullsLast()
// - static comparing()
public class ComparatorMain {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("kim1");
        names.add("kim3");
        names.add("kim4");
        names.add("kim2");
        names.add("lee");

        // =====================
        // [ default method ]
        // =====================
        names.sort(String::compareToIgnoreCase);
        names.forEach(System.out::println);

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);

        // names.sort(compareToIgnoreCase.reversed().thenComparing(..)); // thenComparing: 추가적으로 다른 조건으로 compare 하고자 하는 경우


        // =====================
        // [ static method ]
        // =====================
        // 사용시 찾아보기...

    }
}
