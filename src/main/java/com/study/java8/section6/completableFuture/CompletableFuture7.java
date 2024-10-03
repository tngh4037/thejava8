package com.study.java8.section6.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

// CompletableFuture 을 사용해서 여러 작업들을 조합하는 방법 ( allOf )
public class CompletableFuture7 {

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

        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray) // 2개 이상 정의 가능.
                .thenApply((r) -> {
                    return futures.stream().map(f -> f.join()).collect(Collectors.toList());
                });// 정의한 작업이 모두 끝났을 때 콜백 정의

        results.get().forEach(System.out::println);
    }
}
