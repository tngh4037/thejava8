package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture 을 사용해서 여러 작업들을 조합하는 방법 ( anyOf )
public class CompletableFuture8 {

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

        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world)
                .thenAccept((s) -> {
                    System.out.println(s);
                }); // 작업 중 아무거나 빨리끝나는 것의 결과를 받아서 콜백 실행

        future.get();
    }
}
