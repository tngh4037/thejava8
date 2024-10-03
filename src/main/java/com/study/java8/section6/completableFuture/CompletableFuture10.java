package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture 을 사용해서 예외 처리하는 방법
// - exeptionally(Function)
// - handle(BiFunction)
public class CompletableFuture10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * handle(BiFunction)
         */
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> { // 첫번째 파라미터: 정상적으로 종료됐을 때 결과값, 두 번쨰 파라미터: 에러가 발생했을 때의 에러
            if (ex != null) {
                System.out.println(ex);
                return "ERROR!";
            }
            return result;
        });

        System.out.println(hello.get());
    }
}
