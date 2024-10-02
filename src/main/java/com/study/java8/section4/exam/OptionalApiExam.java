package com.study.java8.section4.exam;

import com.study.java8.section4.OnlineClass;
import com.study.java8.section4.Progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalApiExam {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        // stream api 를 사용할 때도 Optional 을 리턴하는 터미널(종료, 최종) 오퍼레이션들이 몇몇있다. ex) findFirst(), findAny(), reduce(), ...  ( 참고. reduce(): 요소들을 축약하여 Optional로 반환 )
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst(); // spring 으로 시작하는게 있을수도 있고 없을수도 있다는 것.

        boolean present = optional.isPresent(); // 참고) Java 11부터, 그 반대에 해당하는 isEmpty()도 제공한다.
        System.out.println(present);

        if (present) {
            OnlineClass onlineClass = optional.get(); // get(): Optional이 감싸고 있는 타입의 인스턴스를 꺼낼 수 있다. ( 만약 Optional이 감싸고 있는 값이 비어있다면 NoSuchElementException 가 발생한다. -> 런타임 예외가 발생하는 것이다. )
            System.out.println(onlineClass);
        }

        // 위와 같이 if문을 사용해서 값이 비어있는지 체크해서 값을 get()으로 가져올 수도 있지만,
        // 가급적 get()을 사용하지 않고, 아래와 같이 처리하는걸 권장한다.

        // [ Optional 이 제공하는 다양한 API 사용 ]
        // 1) Optional에 값이 있는 경우에 그 값을 가지고 무엇인가를 처리하고 싶다. ( ifPresent )
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 2) 뭔가를 바로 처리하는게 아니라, 값을 가져오고 싶다.
        // 비권장
        OnlineClass onlineClass1 = optional.get();
        // 있으면 가져오고, 없으면 대안책을 가져오겠다.
        OnlineClass onlineClass2 = optional.orElse(createNewClass()); // 주의! 값이 있어도 orElse 구문이 실행은 된다. 따라서 New Class는 무조건 만들어진다. ) => 따라서 상수와 같이 이미 만들어져 있는 것을 가지고 사용할 때는 orElse가 적합할 수 있다. 반대로, 동적으로 실제로 뭔가 작업을 해서 만드는 경우는 orElseGet 을 사용하는 것이 더 적합할 수 있다.
        OnlineClass onlineClass3 = optional.orElseGet(() -> createNewClass()); // 중요! 값이 없는 경우에만 New Class가 만들어진다. )
        // 있으면 가져오고, 없으면 대안책이 없어 예외를 던지겠다.
        OnlineClass onlineClass4 = optional.orElseThrow(); // 참고) default: NoSuchElementException
        OnlineClass onlineClass5 = optional.orElseThrow(() -> { return new IllegalArgumentException(); }); // 참고) 원하는 에러를 던지는 경우
        OnlineClass onlineClass6 = optional.orElseThrow(IllegalStateException::new); // 참고) 원하는 에러를 던지는 경우

        // 3) filter: Optional에 들어있는 값이 있다는 가정하에, 값을 걸러내고자 할때. (없는 경우에는 아무일도 일어나지 않는다.)
        Optional<OnlineClass> onlineClass7 = optional.filter(oc -> oc.getId() > 10); // filter의 결과는 Optional이다. (필터에 해당되면 그 옵셔널 그대로 나오고, 해당이 안되면 비어있는 옵셔널이 나온다.)
        System.out.println(onlineClass7.isEmpty());

        // 4) map: Optional에 들어있는 값 변환하기
        Optional<Integer> onlineClass8 = optional.map(oc -> oc.getId());// map의 결과는 Optional이다. (있으면 옵셔널에 그 값이 나오고, 없으면 비어있는 옵셔널이 나온다.)
        System.out.println(onlineClass8.isPresent());

        // 5) flatMap: map으로 꺼내는 타입 자체가 Optional 타입의 경우. ( Optional 안에 들어있는 인스턴스가 Optional인 경우에 사용하면 편리하다. )
        Optional<Optional<Progress>> onlineClass9 = optional.map((oc) -> {
            return oc.getProgress();
        }); // 리턴타입을 보면, Optional<Optional<Progress>> 이 된다.

        // 그러면 아래와 같이 처리과정이 많아지고 복잡해진다.
        Optional<Progress> p1 = onlineClass9.orElse(Optional.empty());
        Progress p2 = p1.orElseThrow();

        // 따라서 이런 경우 flatMap 을 사용하면, map에서 리턴되는 타입이 Optional인 경우, 그 안에서 껍질을 까준다.
        Optional<Progress> onlineClass10 = optional.flatMap((oc) -> {
            return oc.getProgress();
        });

        // 참고) Optional의 flatMap과 Stream의 flatMap
        // Optional의 flatMap: 값이 존재할 때, 결과를 Optional로 감싸서 평탄화.
        // Stream의 flatMap: 각 요소에 대해 Stream을 생성하고, 이를 평탄화하여 단일 Stream으로 반환.
    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }
}
