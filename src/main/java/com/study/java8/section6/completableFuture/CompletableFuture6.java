package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture 을 사용해서 여러 작업들을 조합하는 방법 ( allOf )
public class CompletableFuture6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Future 간 연관관계가 없는(의존성이 없는) 경우 조합
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(hello, world) // n개 이상의 작업이 들어올 수 있음
                .thenAccept((result) -> {  // allOf의 모든 작업이 다 끝났을 떄 수행할 콜백 ( 그러나 모든 작업의 리턴 타입이 동일하다는 보장도 없고 다를 수 있다. 그리고 그중 어떤것은 에러가 났을수도 있다. 따라서 어떤 특정한 결과를 받을 수 없다. ( Void ) ==> 만약, 받고싶은 경우 CompletableFuture7 참고 )
                    System.out.println(result); // null
                });

        System.out.println(future.get()); // null
    }
}
