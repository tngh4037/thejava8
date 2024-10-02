package com.study.java8.section6.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 고수준 (High-Level) Concurrency 프로그래밍
// - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리. ( 그런 기능을 Executors에게 위임. )

// Executors가 하는 일
// - 쓰레드 만들기: 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
// - 쓰레드 관리: 쓰레드 생명 주기를 관리한다.
// - 작업 처리 및 실행: 쓰레드로 실행할 작업을 제공할 수 있는 API를 제공한다.

// 주요 인터페이스
// - Executor: execute(Runnable)
// - ExecutorService: Executor 를 상속받은 인터페이스로, Callable도 실행할 수 있으며, Executor를 종료 시키거나, 여러 Callable을 동시에 실행하는 등의 기능을 제공한다.
// - ScheduledExecutorService: ExecutorService 를 상속받은 인터페이스로, 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.
public class ExecutorsExam {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(getRunnable("Hello1"));
        executorService.execute(getRunnable("Hello2"));
        executorService.execute(getRunnable("Hello3"));
        executorService.execute(getRunnable("Hello4"));
        executorService.submit(getRunnable("Hello5"));

        executorService.shutdown(); // graceful shutdown ( 현재 진행중인 작업은 다 마치고 끝내는 것 )
        // executorService.shutdownNow(); // 즉시 종료
    }

    private static Runnable getRunnable(String message) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + message);
            }
        };
    }
}
