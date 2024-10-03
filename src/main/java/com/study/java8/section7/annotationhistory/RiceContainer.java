package com.study.java8.section7.annotationhistory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) // 컨테이너 애노테이션의 @Retention 및 @Target 정보는, 자기 자신이 감쌀 애노테이션보다 같거나 더 넓어야 한다. (따라서 평범한 상황에서는 그냥 같게 주면 된다.)
public @interface RiceContainer {
    Rice[] value(); // 그리고 자기 자신이 감싸고 있을 애노테이션을 배열로 가지고 있으면 된다.
}