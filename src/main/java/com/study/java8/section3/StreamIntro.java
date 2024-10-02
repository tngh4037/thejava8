package com.study.java8.section3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamIntro {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("kim1");
        names.add("kim2");
        names.add("kim3");
        names.add("kim4");

        System.out.println("============================");

        Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);

        names.forEach(System.out::println);

        System.out.println("============================");

        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }); // 종료형 오퍼레이션이 실행되지 않았으므로, 스트림의 데이터 소스는 처리되지 않는다. ( 정의만 한 것 )

        System.out.println("============================");

        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList()); // 종료형 오퍼레이션을 실행했으므로, 스트림의 데이터 소스가 처리된다.

        System.out.println("============================");

        List<String> collect = names.parallelStream() // 참고) ForkJoinPool 을 써서 병렬적으로 처리
                .map((s) -> {
                    System.out.println(s + " " + Thread.currentThread().getName()); // 참고) 스레드가 다른 것을 확인할 수 있다.
                    return s.toUpperCase();
                })
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}

// Stream: 연속된 데이터를 처리하는 오퍼레이션들의 모음.
//  ㄴ 참고로 데이터를 담고 있는 저장소(컬렉션)이 아니다.
//  ㄴ 스트림이 처리하는 데이터 소스를 변경하지 않는다. (원본을 변경하지 않는다는 것.)
//
// Stream 이 제공하는 여러 API가 있는데, 이는 크게 두 가지로 나눌 수 있다.
//  ㄴ 중개 오퍼레이션 (중간 오퍼레이션)
//  ㄴ 터미널(종료) 오퍼레이션 (최종 오퍼레이션)
//  ㄴ 참고) 중개 오퍼레이션과 종료 오퍼레이션의 가장 큰 차이는 Stream의 반환 여부이다. ( 중개 오퍼레이션은 스트림을 리턴하고, 종료 오퍼레이션은 스트림이 아닌 다른 타입을 리턴한다. )
//
// Stream 파이프라인 정의
// - 0 또는 다수의 중개 오퍼레이션(intermediate operation)과 한개의 종료 오퍼레이션(terminal operation)으로 구성한다.
// - 스트림의 데이터 소스는 오직 종료(터미널) 오퍼네이션을 실행할 때에만 처리한다.
//
// [ 중개 오퍼레이션 ]
// - 중개 오퍼레이션은 근본적으로 lazy 하다.
//   ㄴ names.stream().map((s) -> {
//          System.out.println(s);
//          return s.toUpperCase();
//      });
//   ㄴ 위와 같이 작성 후 실행한다고 해서, 출력되지(실행되지) 않는다. 정의만 한것뿐이다. ( 종료(터미널) 오퍼레이션을 만날때 까지 실행하지 않는다. , 종료(터미널) 오퍼레이션이 실행되기 전까지는 중개 오퍼레이션은 무의미하다. (실행되지 X) )
// - Stream을 리턴한다.
// - filter, map, limit, skip, sorted, ...
// - Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다.
//   ㄴ 대부분은 Stateless지만 distinct나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.
//
// [ 종료 오퍼레이션 ]
// - Stream을 리턴하지 않는다.
// - collect, allMatch, count, forEach, min, max, ...
//
//
// 참고) parallelStream() 을 사용하면 손쉽게 병렬 처리할 수 있다. ( JVM이 알아서 병렬적으로 처리한다. )
//  : 이 안에서는 spliterator 를 사용해서 스트림을 쪼개서 처리한다.
// 주의) parallelStream() 을 사용한다고 해서 무조건 속도가 빨라지는건 아니다. 오히려 더 느려질 수 있다.
//  : 쓰레드를 만들어서 처리하는 데에는 비용이 든다. ( 컨텍스트 스위칭 비용, 데이터 수집 등 )
//  : 따라서, 데이터가 정말 방대하게 큰 경우에는 성능상 유리할 수 있으나, 그렇지 않은 경우, 한 스레드에서 처리하는게 더 나을 수 있다.
//  : 결론적으로, 성능을 잘 테스트해서 사용하자.
