package com.study.java8.section6.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 참고) java5가 제공하는 Future 만 썼을때는, get()을 호출하기 전에 콜백을 정의하는게 불가능했다.

        // [ 콜백 제공하기 ]
        // 1) thenApply: 결과 값을 받아서 다른 값으로 바꿀수 있는 콜백 (Function이 들어간다.)
        /*
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(future.get()); // get()을 호출해줘야 한다. 호출하지 않으면 아무일도 일어나지 X
         */

        // 2) thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이) | 리턴이 없고 받아서 사용하기만 하는 경우 (Consumer가 들어간다.)
        /*
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        System.out.println(future.get()); // get()을 호출해줘야 한다. 호출하지 않으면 아무일도 일어나지 X
         */

        // 3) thenRun(Runnable): 리턴값 받지 않고 다른 추가 작업을 처리하는 콜백 | 결과를 받지 않고(참고도 못함), 추가적인 작업을 처리 (Runnable이 들어간다.)
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println(future.get()); // get()을 호출해줘야 한다. 호출하지 않으면 아무일도 일어나지 X
    }
}

// 그런데 도대체 스레드풀을 만들지도 않았는데 어떻게 별도의 스레드에서 동작하여 처리되는걸까? -> CompletableFuture3 참고 (ForkJoinPool)
