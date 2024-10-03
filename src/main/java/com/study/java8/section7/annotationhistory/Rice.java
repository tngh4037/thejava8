package com.study.java8.section7.annotationhistory;

import java.lang.annotation.*;

// [ 중복 사용할 수 있는(반복할 수 있는) 애노테이션 만들기 ]
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(RiceContainer.class) // 중복 사용할 수 있도록 하려면 @Repeatable 애노테이션을 사용해서, 여러개의 애노테이션들을 감싸고 있을 컨테이너 애노테이션 타입을 여기다가 지정해주어야 한다.
public @interface Rice {
    String type();
}
