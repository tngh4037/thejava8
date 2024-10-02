package com.study.java8.section4;

import java.util.ArrayList;
import java.util.List;

public class OptionalIntro {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        // NPE 발생 코드
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        // Duration studyDuration = springBootClass.getProgress().getStudyDuration(); // NPE
        // System.out.println(studyDuration);

        // 따라서 보통 아래와 같이 처리한다.
        // Progress progress = springBootClass.getProgress();
        // if (progress != null) {
        //     System.out.println(progress.getStudyDuration());
        // }

        // 그러나 이런식으로 코딩을 할 때에 문제점은,
        // 1) null 체크를 깜빡하면 에러를 유발할 여지가 많다는 것이다. (우리는 사람이기에 당연히 실수할 수 있다.)
        // 2) null 을 리턴하는 것 자체가 문제다.
        //  ㄴ java8 이전에는 별다른 대안이 없었다. 그래서 null인 경우 에러를 던지거나, null을 리턴할 수 밖에 없었다.
        //     ㄴ 에러를 던지거나: 에러가 발생이 되면 자바는 스택 트레이스를 찍는다. (에러가 발생하기 전까지 어떠한 콜스택을 거쳐서 에러가 발생하게 됐는지에 대한 정보를 담음. ) => 그만큼의 리소스를 쓰게된다. (비싸다.) -> 따라서 진짜로 필요한 경우에만 예외를 던지는게 좋다. 그냥 어떤 로직을 처리할때마다 예외를 던지는건 좋은 습관이 아니다.
        //     ㄴ null을 리턴: 그 코드를 사용하는 클리어인트 코드가 주의해야 한다.

        // ====================================================================================

        // [ Java 8 > Optional 의 등장 ]
        // - null 이 전달될 수 있는 경우에 Optional 로 감싸서 리턴할 수 있다.
        // - Optional 은 사용 범위에 제한은 없지만, 리턴타입에만 쓰는 것이다라고 이해하는게 좋다.
    }
}

// Optional
// : 오직 값 한 개가 들어있을 수도 있고, 없을 수도 있는 컨네이너.
// : 메소드에서 작업 중에 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법.
// : 클라이언트에 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.
//
// 주의)
// - 리턴값으로만 쓰기를 권장한다.
//   ㄴ 메소드 매개변수 타입 ( 매개변수로 받는 Optional 자체가 null일 수 있다. 그래서 이에대한 체크가 필요하고, 내부 로직에서 Optional 내부 값이 비어있는지도 체크해야 한다. => Optional을 쓰는 의미가 없음, 더 번거로워짐 ) -> OnlineClass 참고
//   ㄴ 맵의 키 타입 (맵의 key는 null이어서는 안된다는게 map의 가장 큰 특징이다.)
//   ㄴ 인스턴스 필드 타입으로 쓰지 말자. ( ex. public Optional<Progress> progress; )
// - 프리미티브 타입용 Optional이 따로 있다. OptionalInt, OptionalLong,...
//   ㄴ 물론 Optional.of(10); 과 같이 프리미티브 타입을 Optional에 넣을수는 있다.
//   ㄴ 그렇지만, 내부에서 박싱/언박싱이 일어난다. => 박싱/언박싱이 많이 발생할 수록 성능상 좋지 않다.
//   ㄴ 그래서 OptionalInt.of(10); 과 같이 사용하는것이 좋다.
// - Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
//   ㄴ Optional을 리턴하는 메소드에서 null을 리턴하고 있다는 것 자체가, Optional을 사용하는 의미를 무색하게 만드는 것이다. => 이를 사용하는 클라이언트 코드에서 리턴으로 받는 Optional 자체에 대한 null체크를 해줘야한다...
//   ㄴ Optional.empty(); 를 리턴하자.
// - Collection, Map, Stream Array, Optional은 Optional로 감싸지 말 것.
//   ㄴ 컨테이너 성격의 인스턴스들을 Optional로 감싸지 말자.
//   ㄴ Collection, Map, Stream Array, Optional은 전부, 비어있다는 것을 표현할 수 있는 api가 내부에 있다. => 따라서 굳이 Optional로 감쌀 필요가 없다.
//