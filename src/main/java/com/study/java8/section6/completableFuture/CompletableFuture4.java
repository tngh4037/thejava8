package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture 을 사용해서 여러 작업들을 조합하는 방법 ( thenCompose )
public class CompletableFuture4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Future 간 순서가 있는(의존성이 있는) 경우 조합
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> future = hello.thenCompose(s -> getWorld(s)); // 두 작업이 서로 이어서 실행하도록 조합 | 참고) hello의 결과를 받아서 이후 작업 실행

        System.out.println(future.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
