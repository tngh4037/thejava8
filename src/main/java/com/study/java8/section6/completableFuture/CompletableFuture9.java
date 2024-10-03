package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture 을 사용해서 예외 처리하는 방법
// - exeptionally(Function)
// - handle(BiFunction)
public class CompletableFuture9 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * exeptionally(Function)
         */
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> { // error 을 받아서 뭔가를 처리
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(hello.get());
    }
}
