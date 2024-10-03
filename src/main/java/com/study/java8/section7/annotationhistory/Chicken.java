package com.study.java8.section7.annotationhistory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 애노테이션 정보를 언제까지 유지할 것인가?
@Target(ElementType.TYPE_PARAMETER) // 애노테이션을 사용할 곳을 지정한다. ( TYPE_PARAMETER: 제네릭 타입 매개변수에 지정 가능(타입에는 지정 불가) )
public @interface Chicken {

}
