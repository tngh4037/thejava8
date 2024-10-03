package com.study.java8.section7.annotationhistory;

import java.util.Arrays;

@Rice(type = "현미")
@Rice(type = "백미")
@Rice(type = "잡곡")
public class AnnotationHistory2 {

    public static void main(String[] args) {
        // [ 애노테이션 조회 ]
        // 1) 타입(Rice)으로 직접 조회
        Rice[] rices1 = AnnotationHistory2.class.getAnnotationsByType(Rice.class);
        Arrays.stream(rices1).forEach(c -> {
            System.out.println(c.type());
        });

        System.out.println("===============================");

        // 2) 컨테이너 타입으로 조회
        RiceContainer container = AnnotationHistory2.class.getAnnotation(RiceContainer.class);
        Rice[] rices2 = container.value();
        Arrays.stream(rices2).forEach(c -> {
            System.out.println(c.type());
        });
    }
}
