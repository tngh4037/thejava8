package com.study.java8.section7.annotationhistory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) // 애노테이션을 사용할 곳을 지정한다. ( TYPE_USE: TYPE_PARAMETER를 포함해서 타입을 선언하는 모든 곳에 사용 가능 )
public @interface Pizza {

}
