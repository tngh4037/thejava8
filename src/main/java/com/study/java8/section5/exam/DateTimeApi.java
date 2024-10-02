package com.study.java8.section5.exam;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeApi {

    public static void main(String[] args) {

        // [ 기계적인 시간을 사용하는 방법 ]
        Instant instant = Instant.now();
        System.out.println("instant = " + instant); // 기준시 ( UTC(Universal Time Coordinated) == GMT(Greenwich Mean Time) )  ( 참고. 우리나라 시차는 영국 런던 남동쪽에 자리잡은 그리니치 천문대로부터 +9시간 )
        System.out.println("instant = " + instant.atZone(ZoneId.of("UTC"))); // 기준시 ( UTC )

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault()); // 어느 Zone을 기준으로 현재 시간을 볼지 정의
        System.out.println("zonedDateTime = " + zonedDateTime);

        System.out.println("================================================");

        // [ 인류용 일시를 표현하는 방법 ]
        LocalDateTime now = LocalDateTime.now(); // 참고) Local** 이 붙어있으므로, 현재 나의 시스템 zone 정보를 참고해서, 로컬 시간을 가져온다. => 이말인 즉슨, 서버에 배포했는데 서버가 미국에 있다면, 미국 서버의 시스템 기본 zone에 해당하는 시간으로 찍힌다.
        System.out.println("now = " + now);

        LocalDateTime birthday = LocalDateTime.of(2000, Month.JULY, 15, 0, 0, 0);
        System.out.println("birthday = " + birthday);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul")); // 특정 zone의 현재 시간을 보고싶은 경우 => 참고로 이 정보는 Instant 를 사용해서도 알아낼 수 있다. (맨 위 코드 참고)  |  참고) ZonedDateTime <-> Instant 서로 변환이 가능하다.
        System.out.println("ZonedDateTime > nowInKorea = " + nowInKorea);



        // [ 기간을 표현하는 방법 ]
        // 1) 휴먼용 비교: Period
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2024, Month.DECEMBER, 25);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays()); // 오늘부터 특정 일자까지 며칠 남았는지 차이나는 일수

        Period until = today.until(thisYearBirthday);
        System.out.println(until.getDays()); // 오늘부터 특정 일자까지 며칠 남았는지 차이나는 일수
        System.out.println(until.get(ChronoUnit.DAYS)); // 오늘부터 특정 일자까지 며칠 남았는지 차이나는 일수

        // 2) 기계용 비교 : Duration
        Instant now1 = Instant.now();
        Instant plusnow1 = now1.plus(10, ChronoUnit.SECONDS);

        Duration between = Duration.between(now1, plusnow1);
        System.out.println("between.getSeconds(): " + between.getSeconds());



        // [ 포맷팅 ]
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("nowDateTime: " + nowDateTime); // 2024-10-03T06:36:50.250710100

        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // 참고) 직접 지정할 수도 있지만, 자바에서 미리 정의해둔 포맷도 있으니 참고하자. ( https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined )
        System.out.println("nowDateTime.format(MM/dd/yyyy): " + nowDateTime.format(MMddyyyy));

        // [ 파싱 ]
        LocalDate parse = LocalDate.parse("07/15/2000", MMddyyyy);
        System.out.println("parse = " + parse);



        // [ 레거시 API 지원 ] - 예전 API와 호환된다.
        Date date = new Date();
        Instant instant1 = date.toInstant(); // date -> instant
        Date newDate = Date.from(instant1); // instant -> date

        // instant 로만 바꿀수만 있으면 무조건 다 최신API로 바꿀수있다. ( ex. instant -> zonedDateTime, instant -> localDatetime, .. )
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime zonedDateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()); // calendar(gregorianCalendar) -> instant -> zonedDateTime
        LocalDateTime localDateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); // calendar(gregorianCalendar) -> instant -> zonedDateTime -> LocalDateTime
        LocalDateTime localDateTime2 = gregorianCalendar.toZonedDateTime().toLocalDateTime();// calendar(gregorianCalendar) -> zonedDateTime -> LocalDateTime

        // 반대로도 가능하다. ( 최신API -> 레거시API )
        // zonedDateTime -> calendar(gregorianCalendar)
        GregorianCalendar from = GregorianCalendar.from(zonedDateTime1);
        // 과거 TimeZone -> 최신 ZoneId
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        // 최신 ZoneId -> 과거 TimeZone
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);



        // [ 연산 ]
        LocalDateTime plusDays = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
    }
}

