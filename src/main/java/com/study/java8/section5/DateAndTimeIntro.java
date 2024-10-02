package com.study.java8.section5;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAndTimeIntro {

    public static void main(String[] args) throws InterruptedException {

        // [ Java 8 이전에 날짜를 다루기 위해 주로 사용했던 클래스들: Date, Calendar, DateFormat ]
        // 단점)
        // - 클래스 이름이 명확하지 않다. Date인데 시간까지 다룬다.
        // - 그전까지 사용하던 java.util.Date 클래스는 mutable 하기 때문에 thead safe하지 않다.
        // - 버그가 발생할 여지가 많다. (타입 안정성이 없고, 월이 0부터 시작한다거나..)
        // - 이러한 불편함들과 문제들로 인해, 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time을 쓰곤했다.
        Date date = new Date();
        DateFormat dt = new SimpleDateFormat();

        long time = date.getTime(); // Date(날짜)인데 시간을 가져온다(?)
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);
        System.out.println(after3Seconds); // 인스턴스 안에 있는 데이터(상태)가 바뀜 (mutable) => mutable한 객체들은 멀티쓰레드 환경에서 thread safe 하지 않다.

        Calendar gregorianCalendar1 = new GregorianCalendar(2001, 1 , 15); // 버그: 월이 0부터 시작 (1은 2월임)
        Calendar gregorianCalendar2 = new GregorianCalendar(2001, Calendar.FEBRUARY , 15); // 따라서 아래와 같이 상수로 정의해주는게 권장된다.
        Calendar gregorianCalendar3 = new GregorianCalendar(2001, -50 , 15); // 타입 안정성이 없다. (int로 받기 때문에, 아무 값이나 들어간다.) => ( 참고. 만약 type safe 하게 바꾸려면? Momth 라는 enum 타입으로 받도록 한다던지 등. )
    }
}

// [ 자바 8에서 제공하는 Date-Time API ]
// 디자인 철학
// - Clear: api는 명확해야 한다. (date에서 time을 꺼내고..?)
// - Fluent: null 을 리턴하거나 하지 않으므로, 계속해서 체이닝 형식으로 사용할 수 있도록 유연하게 설계되어 있다.
// - Immutable: 기존 인스턴스(원본)가 바뀌지 않고, 새로운 인스턴스가 만들어짐.
// - Extensible: 여러가지 다른 날짜 시스템(불교달력, 타국 등)을 지원한다.


// 주요 API를 크게 두 가지로 나눌 수 있다.
// 1) 기계용 시간: EPOCK (1970년 1월 1일 0시 0분 0초)부터 현재까지의 타임스탬프를 표현한다. ( 타임스탬프는 Instant를 사용한다. )
// 2) 사람용 시간: 우리가 흔히 사용하는 연,월,일,시,분,초 등을 표현한다.
//   ㄴ 특정 날짜(LocalDate), 시간(LocalTime), 일시(LocalDateTime)를 사용할 수 있다.
//   ㄴ 기간을 표현할 때는 Duration(시간 기반)과 Period(날짜 기반)를 사용할 수 있다.
//   ㄴ DateTimeFormatter를 사용해서 일시를 특정한 문자열로 포매팅할 수 있다.
