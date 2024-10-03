package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture 을 사용해서 여러 작업들을 조합하는 방법 ( thenCombine )
public class CompletableFuture5 {

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

        CompletableFuture<String> future = hello.thenCombine(world, (hResult, wResult) -> {
            return hResult + " " + wResult;
        }); // 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행 | 참고) hello와 world를 조합하고, 둘의 결과를 받아서 무언가 처리 ( BiFunction )

        System.out.println(future.get());
    }
}
