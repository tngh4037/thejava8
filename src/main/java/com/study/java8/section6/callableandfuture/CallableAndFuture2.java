package com.study.java8.section6.callableandfuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(2000);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(5000);
            return "Java";
        };

        Callable<String> spring = () -> {
            Thread.sleep(1000);
            return "Spring";
        };

        // 여러 작업 동시에 실행하기: invokeAll()
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, spring)); // 참고) 동시에 실행한 작업 중에 제일 오래 걸리는 작업 만큼 시간이 걸린다. ( 모두 처리가 완료될 때 까지 기다린다. )
        for (Future<String> f : futures) { // 모든 작업이 끝나고 한번에 출력
            System.out.println(f.get());
        }

        System.out.println("=======================");

        // 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기: invokeAny()
        String s = executorService.invokeAny(Arrays.asList(hello, java, spring)); // 참고) 동시에 실행한 작업 중에 제일 짧게 걸리는 작업 만큼 시간이 걸린다. | 참고) 리턴되는 s는 작업 중 가장 짧은 것에 해당한다. ("Spring")
        System.out.println(s);

        executorService.shutdown();
    }
}
