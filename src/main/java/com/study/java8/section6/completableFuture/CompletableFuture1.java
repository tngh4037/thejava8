package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// [ CompletableFuture (외부에서 Complete 할 수있는 Future) ]
// 자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 기술. ( Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많았다. )
//
// [ Future 한계 ]
// - Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
// - 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
// - 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기
// - 예외 처리용 API를 제공하지 않는다.
public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // [ 비동기로 작업 실행하기 ]
        // CompletableFuture<String> future1 = new CompletableFuture<>(); // 참고) 우리가 직접 별다른 Executor를 사용하지 않아도, 내부적으로 ForkJoinPool 에 있는 commonPool을 사용한다. ( 참고로 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다. 적용예시는 CompletableFuture3 를 참고하자. ) | ( 기본은 ForkJoinPool.commonPool() )
        // future1.complete("kim");
        // String result = future1.get();
        // System.out.println("result = " + result);

        // 1) 리턴값이 없는 경우: runAsync()
        // CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
        //     System.out.println("Hello1 " + Thread.currentThread().getName());
        // });
        // System.out.println(future2.get()); // get()을 해야 비로소 위 Runnable 이 실행됨. get()을 하지 않으면 아무일도 일어나지 X

        // 2) 리턴값이 있는 경우: supplyAsync()
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello2 " + Thread.currentThread().getName());
            return "Hello2";
        });
        System.out.println(future3.get());

        // 지금까지 살펴본 위 코드는 사실 코드만 조금 달려졌을 뿐이지, Future 를 사용하던 떄와 거의 비슷하다. ( ExecutorService에다가 작업 넘겨주고, get() 호출해서 블로킹한 다음에 결과 가져오는 것 )
        //
        // 그러면, 결과가 왔을때 비동기적으로 특정 콜백을 실행했으면 좋겠다면? -> CompletableFuture2 참고
    }
}

// 참고) void / Void
// - void: 메서드가 값을 반환하지 않을 때 사용.
// - Void: 제네릭에서 결과가 필요 없는 경우에 사용되는 특별한 타입.
