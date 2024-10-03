package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuture3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다. ( 기본은 ForkJoinPool.commonPool() )
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executorService);

        future.get();

        executorService.shutdown();
    }
}
