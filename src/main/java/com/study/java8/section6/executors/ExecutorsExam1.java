package com.study.java8.section6.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExam1 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(getRunnable("Hi"), 3, TimeUnit.SECONDS); // 3초뒤 실행
        executorService.shutdown();
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
